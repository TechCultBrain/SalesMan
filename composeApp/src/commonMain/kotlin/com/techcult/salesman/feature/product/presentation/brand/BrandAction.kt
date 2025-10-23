package com.techcult.salesman.feature.product.presentation.brand

sealed interface BrandAction {
    data class OnSearchQueryChange(val query: String) : BrandAction
    object OnAddClick : BrandAction
    data class OnEditClick(val brandId: Long) : BrandAction
    data class OnBrandNameChange(val brandName: String) : BrandAction
    data class OnBrandImageChange(val brandImage: String) : BrandAction
    data class OnBrandDescriptionChange(val brandDescription: String) : BrandAction
    data class OnActiveChange(val isActive: Boolean) : BrandAction
    object OnSaveClick : BrandAction
    data class OnBrandAddDialogToggle(val isAddBrandDialogOpen: Boolean) : BrandAction
    data class OnStatusChange(val isActive: Boolean) : BrandAction

    data class OnAddImageClick(val isAddImageDialogOpen: Boolean) : BrandAction

}