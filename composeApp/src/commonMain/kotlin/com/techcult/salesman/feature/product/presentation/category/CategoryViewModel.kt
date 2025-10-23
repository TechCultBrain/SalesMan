package com.techcult.salesman.feature.product.presentation.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcult.salesman.core.domain.onError
import com.techcult.salesman.core.domain.onSuccess
import com.techcult.salesman.core.utils.errorToMessage
import com.techcult.salesman.feature.product.domain.repository.CategoryRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class CategoryViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {

    private val _state = MutableStateFlow(CategoryState())
    val state = _state
        .asStateFlow()

    private val _event = Channel<CategoryEvent>()
    val event = _event.receiveAsFlow()


    init {

        viewModelScope.launch {
            loadCategoryList()
        }


    }

    fun onAction(action: CategoryAction) {
        when (action) {
            CategoryAction.OnAddClick -> {
                resetState()
                _state.update {
                    it.copy(
                        isAddCategoryDialogOpen = true
                    )
                }
            }

            is CategoryAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(
                        searchQuery = action.query
                    )
                }
                viewModelScope.launch {
                    categoryRepository.searchCategories(_state.value.searchQuery)
                        .collect { newList ->
                            _state.update {
                                it.copy(
                                    categoryList = newList, isLoading = false
                                )
                            }
                        }
                }
            }

            is CategoryAction.OnActiveChange -> {
                _state.update {
                    it.copy(
                        isActive = action.isActive
                    )
                }
            }

            is CategoryAction.OnCategoryDescriptionChange -> {
                _state.update {
                    it.copy(
                        catDescription = action.categoryDescription
                    )
                }
            }

            is CategoryAction.OnCategoryImageChange -> {
                _state.update {
                    it.copy(
                        catImage = action.categoryImage, isAddImageDialogOpen = false
                    )
                }
            }

            is CategoryAction.OnCategoryNameChange -> {
                _state.update {
                    it.copy(
                        catName = action.categoryName
                    )
                }
            }

            is CategoryAction.OnEditClick -> {
                _state.update {
                    it.copy(
                        isEditMode = true, isAddCategoryDialogOpen = true
                    )
                }
                viewModelScope.launch {
                    loadCategoryById(action.categoryId)
                }
            }

            CategoryAction.OnSaveClick -> {
                _state.update {
                    it.copy(isLoading = true)
                }
                viewModelScope.launch {
                    saveCategory()
                }
            }

            is CategoryAction.OnTopCategoryChange -> {
                _state.update {
                    it.copy(
                        topCategoryId =_state.value.categoryList.
                        find { category -> category.categoryName == action.categoryName }?.categoryId,
                        isTopCategoryDropDownOpen = false,
                        topCategoryName = action.categoryName

                    )
                }
            }
            is CategoryAction.OnTopCategoryDropDownToggle -> {
                _state.update {
                    it.copy(
                        isTopCategoryDropDownOpen = action.isTopCategoryDropDownOpen
                    )
                }

            }
            is CategoryAction.OnCategoryAddDialogToggle -> {
                _state.update {
                    it.copy(
                        isAddCategoryDialogOpen = action.isAddCategoryDialogOpen
                    )
                }
            }
            is CategoryAction.OnAddImageClick -> {
                _state.update {
                    it.copy(
                        isAddImageDialogOpen = action.isAddImageDialogOpen
                    )
                }
            }

        }

    }

    @OptIn(ExperimentalTime::class)
    private suspend fun saveCategory() {
        if (validateCategory()) {
            val category = com.techcult.salesman.feature.product.domain.model.Category(
                categoryId = _state.value.catId,
                categoryName = _state.value.catName,
                description = _state.value.catDescription,
                active = _state.value.isActive,
                createdAt = Clock.System.now().toLocalDateTime(timeZone = TimeZone.currentSystemDefault()),
                updatedAt = if (_state.value.isEditMode) {
                    Clock.System.now().toLocalDateTime(timeZone = TimeZone.currentSystemDefault())
                } else null,
                categoryImage = _state.value.catImage,
                categoryDescription = _state.value.catDescription,
                topCategoryId = _state.value.topCategoryId

            )
            categoryRepository.insertorUpdateCategory(category).onSuccess {
                if (_state.value.isEditMode) {
                    _event.send(CategoryEvent.UpdateCategorySuccess)
                } else {
                    _event.send(CategoryEvent.SaveCategorySuccess)
                }
            }
                .onError {
                    if (_state.value.isEditMode) {
                        _event.send(CategoryEvent.UpdateCategoryError(errorToMessage(it)))
                    } else {
                        _event.send(CategoryEvent.SaveCategoryError(errorToMessage(it)))
                    }

                }
            resetState()
        }
    }

    private fun validateCategory(): Boolean {
        if (_state.value.catName.isBlank()) {
            _state.update {
                it.copy(catNameError = "Category name is required")
            }
            return false
        } else {
            return true
        }
    }

    private suspend fun loadCategoryById(categoryId: Long) {
        val category = categoryRepository.getCategoryById(categoryId)
        category?.let {
            _state.update {
                it.copy(
                    catId = category.categoryId,
                    catName = category.categoryName,
                    catImage = "",
                    catDescription = category.description.toString(),
                    isActive = category.active,
                    topCategoryId = category.topCategoryId,
                )
            }
        }

    }

    private fun resetState() {
        _state.update {
            it.copy(
                catId = null,
                catName = "",
                catImage = "",
                catDescription = "",
                isActive = true,
                isEditMode = false,
                isAddCategoryDialogOpen = false
            )
        }

    }

    private suspend fun loadCategoryList() {
        categoryRepository.getAllCategories().collect { newList ->
            _state.update {
                it.copy(
                    categoryList = newList, isLoading = false
                )
            }
        }

    }
}