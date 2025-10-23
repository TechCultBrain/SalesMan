package com.techcult.salesman.feature.tax.presentation

interface TaxUiAction {

    data object OnSaveClick : TaxUiAction
    data object OnCancelClick : TaxUiAction

    data class OnTaxAddDialogOpen(val isOpen: Boolean) : TaxUiAction
    data class OnTaxNameChange(val name: String) : TaxUiAction
    data class OnTaxComponentNameChange(val name: String, val index: Int) : TaxUiAction
    data class OnTaxComponentValueChange(val value: String, val index: Int) : TaxUiAction

    data object OnTaxAddClick : TaxUiAction


}