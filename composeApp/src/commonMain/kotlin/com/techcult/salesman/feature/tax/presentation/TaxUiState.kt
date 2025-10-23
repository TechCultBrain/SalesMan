package com.techcult.salesman.feature.tax.presentation

import com.techcult.salesman.feature.tax.data.TaxSlabWithComponents

data class TaxUiState(
    val isAddDialogOpen: Boolean = false,
    val searchQuery: String = "",
    val isEditMode: Boolean = false,
    val taxSlabName: String = "",
    val taxComponents: MutableList<TaxComponent> = mutableListOf(
        TaxComponent("SGst", ""),
        TaxComponent("CGst", "")
    ),
    val taxSaveError: String? = null,
    val taxSlabs: List<TaxSlabWithComponents> = emptyList()


)

data class TaxComponent(val taxName: String, val taxValue: String)