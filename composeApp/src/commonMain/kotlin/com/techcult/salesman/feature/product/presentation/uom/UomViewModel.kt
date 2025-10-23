package com.techcult.salesman.feature.product.presentation.uom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcult.salesman.core.domain.onError
import com.techcult.salesman.core.domain.onSuccess
import com.techcult.salesman.core.utils.errorToMessage
import com.techcult.salesman.feature.product.domain.model.Uom
import com.techcult.salesman.feature.product.domain.repository.UomRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UomViewModel(val uomRepository: UomRepository) : ViewModel() {


    private val _state = MutableStateFlow(UomState())
    val state = _state
        .asStateFlow()

    private val _event = Channel<UomEvents>()
    val event = _event.receiveAsFlow()


    init {

        viewModelScope.launch {
            loadUom()
        }


    }


    fun onAction(action: UomAction) {
        when (action) {
            is UomAction.OnActiveChange -> {
                _state.update {
                    it.copy(
                        isActive = action.isActive
                    )
                }
            }

            UomAction.OnAddClick -> {
                resetState()
                _state.update {
                    it.copy(
                        isAddUomDialogOpen = true
                    )
                }
            }

            is UomAction.OnAddUomDialogOpen -> {
                _state.update {
                    it.copy(
                        isAddUomDialogOpen = action.isAddUomDialogOpen
                    )
                }
            }

            is UomAction.OnCategoryChange -> {
                _state.update {
                    it.copy(
                        uomCategory = action.category, isCategoryDialogOpen = false
                    )
                }
            }

            is UomAction.OnCategoryClick -> {
                _state.update {
                    it.copy(
                        isCategoryDialogOpen = action.isCategoryDialogOpen
                    )
                }
            }

            is UomAction.OnBaseDropDownOpen -> {
                _state.update {
                    it.copy(
                        isBaseDropOpen = action.isBaseDropOpen
                    )
                }

            }


            UomAction.OnSaveClick -> {
                _state.update {
                    it.copy(isLoading = true)
                }
                viewModelScope.launch {

                    saveUom()
                }
            }

            is UomAction.OnUomCategoryChange -> {
                _state.update {
                    it.copy(
                        uomCategory = action.uomCategory, uomCategoryError = null
                    )
                }
            }

            is UomAction.OnUomDescriptionChange -> {
                _state.update {
                    it.copy(
                        uomDescription = action.uomDescription,
                    )
                }
            }

            is UomAction.OnUomNameChange -> {
                _state.update {
                    it.copy(
                        uomName = action.uomName, uomNameError = null
                    )
                }
            }

            is UomAction.OnUomSymbolChange -> {
                _state.update {
                    it.copy(
                        uomSymbol = action.uomSymbol, uomSymbolError = null
                    )
                }

            }

            is UomAction.OnBaseUomChange -> {
                _state.update {
                    it.copy(
                        baseUomId = _state.value.uomList.find { it.name == action.uomName }?.uomId,
                        baseUomName = action.uomName

                    )
                }
            }


            is UomAction.OnConversionFactorChange -> {
                _state.update {
                    it.copy(
                        conversionFactor = action.conversionFactor
                    )
                }

            }

            is UomAction.OnStatusChange -> {
                _state.update {
                    it.copy(
                        isActive = action.isActive
                    )
                }
            }

            is UomAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(
                        searchQuery = action.searchQuery
                    )
                }
                viewModelScope.launch {
                    uomRepository.searchUom(_state.value.searchQuery).collect { uoms ->
                        _state.update {
                            it.copy(
                                uomList = uoms, isLoading = false
                            )
                        }
                    }
                }
            }

            is UomAction.OnDeleteClick -> {

            }

            is UomAction.OnEditClick -> {
                _state.update {
                    it.copy(
                        isEditMode = true, isAddUomDialogOpen = true
                    )
                }
                viewModelScope.launch {
                    loadUomById(action.uomId)
                }

            }

        }
    }

    suspend fun loadUom() {
        uomRepository.getAllUom().collect { newList ->
            _state.update {
                it.copy(
                    uomList = newList, isLoading = false
                )

            }
        }

    }


    private suspend fun loadUomById(uomId: Long) {
        val uom = uomRepository.getUomById(uomId)
        uom?.let { uom ->
            _state.update {
                it.copy(
                    uomId = uom.uomId,
                    uomName = uom.name,
                    uomSymbol = uom.symbol,
                    uomCategory = uom.uomCategory,
                    uomDescription = uom.description.toString(),
                    isActive = uom.isActive,
                    isBaseUom = uom.baseUomId != null,
                    baseUomId = if (it.baseUomId == null) null else uom.baseUomId,
                    conversionFactor = it.conversionFactor,
                    baseUomName = uom.baseUomName

                )
            }


        }
    }

    private suspend fun saveUom() {
        if (validateUom()) {
            val uom = Uom(
                uomId = _state.value.uomId,
                name = _state.value.uomName,
                symbol = _state.value.uomSymbol,
                uomCategory = _state.value.uomCategory,
                baseUomId = _state.value.baseUomId,
                conversionFactor = _state.value.conversionFactor,
                isActive = _state.value.isActive,
                description = _state.value.uomDescription,
                baseUomName = _state.value.baseUomName
            )
            uomRepository.insertOrUpdate(uom).onSuccess {
                if (_state.value.isEditMode) {
                    _event.send(UomEvents.UpdateUomSuccess)
                } else {
                    _event.send(UomEvents.SaveUomSuccess)
                }

            }
                .onError {
                    if (_state.value.isEditMode) {
                        _event.send(UomEvents.UpdateUomError(errorToMessage(it)))
                    } else {
                        _event.send(UomEvents.SaveUomError(errorToMessage(it)))
                    }

                }
            resetState()


        }
    }

    private fun resetState() {
        _state.update {
            it.copy(
                uomId = null,
                uomName = "",
                uomSymbol = "",
                uomCategory = "",
                uomDescription = "",
                isActive = true,
                isEditMode = false,
                isAddUomDialogOpen = false,
                uomNameError = null,
                uomSymbolError = null
            )

        }
    }

    private fun validateUom(): Boolean {
        return if (_state.value.uomName.isBlank()) {
            _state.update {
                it.copy(uomNameError = "Uom name is required")
            }
            false
        } else if (_state.value.uomSymbol.isBlank()) {
            _state.update {
                it.copy(uomSymbolError = "Uom symbol is required")
            }
            false
        } else if (_state.value.uomCategory.isBlank()) {
            _state.update {
                it.copy(uomCategoryError = "Uom category is required")
            }

            false
        } else {
            true
        }
    }


}