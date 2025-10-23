package com.techcult.salesman.feature.product.presentation.brand

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcult.salesman.core.domain.onError
import com.techcult.salesman.core.domain.onSuccess
import com.techcult.salesman.core.utils.errorToMessage
import com.techcult.salesman.feature.product.domain.model.Brand
import com.techcult.salesman.feature.product.domain.repository.BrandRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class BrandViewModel(val brandRepository: BrandRepository) : ViewModel() {

    private val _state = MutableStateFlow(BrandState())
    val state = _state.asStateFlow()

    private val _event = Channel<BrandEvent>()
    val event = _event.receiveAsFlow()


    init {

        viewModelScope.launch {
            loadBrandList()
        }


    }

    fun onAction(action: BrandAction) {
        when (action) {
            is BrandAction.OnActiveChange -> {
                _state.update {
                    it.copy(
                        isActive = action.isActive
                    )
                }
            }

            BrandAction.OnAddClick -> {
                resetState()
                _state.update {
                    it.copy(
                        isAddBrandDialogOpen = true
                    )
                }
            }

            is BrandAction.OnBrandDescriptionChange -> {
                _state.update {
                    it.copy(
                        brandDescription = action.brandDescription
                    )
                }
            }

            is BrandAction.OnBrandImageChange -> {
                _state.update {
                    it.copy(
                        brandImage = action.brandImage, isAddImageDialogOpen = false
                    )
                }
            }

            is BrandAction.OnBrandNameChange -> {
                _state.update {
                    it.copy(
                        brandName = action.brandName, brandSaveError = ""
                    )
                }
            }

            is BrandAction.OnEditClick -> {
                _state.update {
                    it.copy(
                        isEditMode = true, isAddBrandDialogOpen = true
                    )
                }
                viewModelScope.launch {
                    loadBrandById(action.brandId)
                }
            }

            is BrandAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(
                        searchQuery = action.query
                    )
                }
                viewModelScope.launch {
                    brandRepository.searchBrands(_state.value.searchQuery).collect { newList ->
                        _state.update {
                            it.copy(
                                brandList = newList, isLoading = false
                            )
                        }
                    }
                }

            }

            BrandAction.OnSaveClick -> {
                _state.update {
                    it.copy(isLoading = true)
                }
                viewModelScope.launch {
                    saveBrand()
                }
            }

            is BrandAction.OnBrandAddDialogToggle -> {
                _state.update {
                    it.copy(
                        isAddBrandDialogOpen = action.isAddBrandDialogOpen
                    )
                }
            }

            is BrandAction.OnStatusChange -> {
                _state.update {
                    it.copy(
                        isActive = action.isActive
                    )
                }
            }

            is BrandAction.OnAddImageClick -> {
                _state.update {
                    it.copy(
                        isAddImageDialogOpen = true
                    )
                }
            }
        }

    }

    private suspend fun saveBrand() {
        _state.update {
            it.copy(isLoading = true)
        }
        if (validateBrand()) {
            val brand = Brand(
                brandId = _state.value.brandId,
                brandName = _state.value.brandName,
                image = _state.value.brandImage,
                description = _state.value.brandDescription,
                isActive = _state.value.isActive,
                updatedAt = if (_state.value.isEditMode) Clock.System.now()
                    .toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault()) else null,
                brandCode = _state.value.brandName.take(3).toUpperCase(locale = Locale.current),

            )
            brandRepository.insertOrUpdate(brand).onSuccess {
                if (_state.value.isEditMode) {
                    _event.send(BrandEvent.UpdateBrandSuccess)
                } else {
                    _event.send(BrandEvent.SaveBrandSuccess)
                }
            }.onError {
                    if (_state.value.isEditMode) {
                        _event.send(BrandEvent.UpdateBrandError(errorToMessage(it)))
                    } else {
                        _event.send(BrandEvent.SaveBrandError(errorToMessage(it)))
                    }

                }
               resetState()

        }
    }

    private fun validateBrand(): Boolean {
        return if (_state.value.brandName.isBlank()) {
            _state.update {
                it.copy(brandSaveError = "Brand name is required")
            }
            false
        } else {
            true
        }
    }

    private suspend fun loadBrandById(brandId: Long) {
        val brand = brandRepository.getBrandById(brandId)
        brand?.let {
            _state.update {
                it.copy(
                    brandId = brand.brandId,
                    brandName = brand.brandName,
                    brandImage = brand.image.toString(),
                    brandDescription = brand.description.toString(),
                    isActive = brand.isActive,

                )
            }
        }
    }

    private fun resetState() {
        _state.update {
            it.copy(
                brandId = null,
                brandName = "",
                brandImage = "",
                brandDescription = "",
                isActive = true,
                isEditMode = false,
                isAddBrandDialogOpen = false,

                )
        }
    }

    private suspend fun loadBrandList() {
        brandRepository.getAllBrands().collect { newList ->
            _state.update {
                it.copy(
                    brandList = newList, isLoading = false
                )
            }
        }
    }
}