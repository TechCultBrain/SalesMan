package com.techcult.salesman.feature.product.presentation.category

sealed interface CategoryAction {
    data class OnSearchQueryChange(val query: String) : CategoryAction
    object OnAddClick : CategoryAction

    data class OnEditClick(val categoryId: Long) : CategoryAction
    data class OnActiveChange(val isActive: Boolean) : CategoryAction
    data class OnCategoryDescriptionChange(val categoryDescription: String) : CategoryAction
    data class OnCategoryAddDialogToggle(val isAddCategoryDialogOpen: Boolean) : CategoryAction
    data class OnCategoryImageChange(val categoryImage: String) : CategoryAction
    data class OnCategoryNameChange(val categoryName: String) : CategoryAction
    object OnSaveClick : CategoryAction
    data class OnTopCategoryChange(val categoryName: String) : CategoryAction
    data class OnTopCategoryDropDownToggle(val isTopCategoryDropDownOpen: Boolean) : CategoryAction
    data class OnAddImageClick(val isAddImageDialogOpen: Boolean) : CategoryAction

    data class OnStatusChange(val isActive: Boolean)





}