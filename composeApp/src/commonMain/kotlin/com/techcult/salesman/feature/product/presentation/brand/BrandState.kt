package com.techcult.salesman.feature.product.presentation.brand

import com.techcult.salesman.feature.product.domain.model.Brand

data class BrandState(
    val isLoading: Boolean = false,
    val brandList: List<Brand> = emptyList(),
    val brandName: String = "",
    val brandImage: String? = null,
    val brandDescription: String = "",
    val isAddBrandDialogOpen: Boolean = false,
    val isActive: Boolean = true,
    val brandSaveError: String? = null,
    val isEditMode: Boolean = false,
    val searchQuery: String = "",
    val brandId: Long? = null,
    val isAddImageDialogOpen: Boolean = false,
)
