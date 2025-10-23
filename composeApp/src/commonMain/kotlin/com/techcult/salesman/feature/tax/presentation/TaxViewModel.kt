package com.techcult.salesman.feature.tax.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcult.salesman.feature.tax.data.TaxSlabWithComponents
import com.techcult.salesman.feature.tax.domain.TaxRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaxViewModel(val taxRepository: TaxRepository) : ViewModel() {


    private val _state = MutableStateFlow(TaxUiState())
    val state: StateFlow<TaxUiState> = _state.asStateFlow()

    init {

        viewModelScope.launch {
            taxRepository.getAllActiveTaxSlabs().collect { taxSlabs ->
                _state.update {
                    it.copy(taxSlabs = taxSlabs)
                }
            }
        }
    }

    fun onAction(action: TaxUiAction) {
        when (action) {
            is TaxUiAction.OnTaxAddDialogOpen -> {
                _state.update {
                    it.copy(isAddDialogOpen = action.isOpen)
                }

            }

            is TaxUiAction.OnTaxNameChange -> {
                _state.update {
                    it.copy(taxSlabName = action.name)
                }
            }

            TaxUiAction.OnTaxAddClick -> {
                val updatedList = _state.value.taxComponents.toMutableList() + TaxComponent("", "")
                _state.update {
                    it.copy(taxComponents = updatedList.toMutableList())
                }

            }

            TaxUiAction.OnCancelClick -> {
                _state.update {
                    it.copy(isAddDialogOpen = false)
                }

            }

            TaxUiAction.OnSaveClick -> {
                _state.update {
                    it.copy(isAddDialogOpen = false)
                }
                val pailList = _state.value.taxComponents.map {
                    Pair(it.taxName, it.taxValue.toDouble())
                }

                viewModelScope.launch {
                    taxRepository.createTaxSlab(
                        _state.value.taxSlabName,
                        pailList
                    )
                }


            }

            is TaxUiAction.OnTaxComponentNameChange -> {
                val updatedList = _state.value.taxComponents.toMutableList()
                updatedList[action.index] = updatedList[action.index].copy(taxName = action.name)
                _state.update {
                    it.copy(taxComponents = updatedList)
                }
            }

            is TaxUiAction.OnTaxComponentValueChange -> {
                val updatedList = _state.value.taxComponents.toMutableList()
                updatedList[action.index] = updatedList[action.index].copy(taxValue = action.value)
                _state.update {
                    it.copy(taxComponents = updatedList)
                }

            }


        }
    }


}