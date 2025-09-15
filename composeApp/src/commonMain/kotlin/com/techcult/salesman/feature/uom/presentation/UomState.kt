package com.techcult.salesman.feature.uom.presentation

import com.techcult.salesman.feature.uom.domain.Uom
import com.techcult.salesman.feature.uom.utils.uomCategories

data class UomState(
    val isLoading: Boolean = false,
    val uomList: List<Uom> = emptyList(),
    val uomCategoryList: List<String> = uomCategories,
    val uomName: String = "",
    val uomSymbol: String = "",
    val uomCategory: String = "",
    val uomDescription: String = "",
    val isAddUomDialogOpen: Boolean = false,
    val isActive: Boolean = false,
    val uomSaveError: String? = null,
    val isEditMode: Boolean = false,
    val isBaseUom: Boolean = false,
    val baseUomId: Long? = null,
    val conversionFactor: Double=1.0,
    val searchQuery: String = "",
    val uomId: Long? = null,
    val isCategoryDialogOpen: Boolean = false,
    val baseUomList: List<Uom> = emptyList(),
    val isBaseDropOpen: Boolean = false,
    val uomNameError: String? = null,
    val uomSymbolError: String? = null,
    val uomCategoryError: String? = null,


)
