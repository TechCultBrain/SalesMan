package com.techcult.salesman.feature.uom.presentation

sealed interface UomAction {
    object OnAddClick : UomAction
    object OnSaveClick : UomAction
    data class OnUomNameChange(val uomName: String) : UomAction
    data class OnUomSymbolChange(val uomSymbol: String) : UomAction
    data class OnUomCategoryChange(val uomCategory: String) : UomAction
    data class OnUomDescriptionChange(val uomDescription: String) : UomAction
    data class OnActiveChange(val isActive: Boolean) : UomAction
    data class OnAddUomDialogOpen(val isAddUomDialogOpen: Boolean) : UomAction
    data class OnBaseUomChange(val uomId: String?)  : UomAction
    data class OnBaseUomIdChange(val baseUomId: Long) : UomAction
    data class OnConversionFactorChange(val conversionFactor: Double) : UomAction
    data class OnSearchQueryChange(val searchQuery: String) : UomAction

    data class OnEditClick(val uomId: Long) : UomAction
    data class OnCategoryClick(val isCategoryDialogOpen: Boolean) : UomAction
    data class OnCategoryChange(val category: String) : UomAction
    data class OnBaseDropDownOpen(val isBaseDropOpen: Boolean) : UomAction
    data class OnBaseUnitSelected(val baseUom: String) : UomAction


}

