package com.techcult.salesman.feature.product.presentation.category

import com.techcult.salesman.feature.product.domain.model.Category


data class CategoryState(
    val isLoading: Boolean = false,
    val categoryList: List<Category> = emptyList(),
    val catName: String = "",
    val catImage: String = "",
    val catDescription: String = "",
    val isAddCategoryDialogOpen: Boolean = false,
    val isActive: Boolean = true,
    val catSaveError: String? = null,
    val isEditMode: Boolean = false,
    val isTopCategory: Boolean = false,
    val topCategoryId: Long? = null,
    val searchQuery: String = "",
    val catId: Long? = null,
    val topCategoryList: List<Category> = emptyList(),
    val isTopDropOpen: Boolean = false,
    val catNameError: String? = null,
    val catSymbolError: String? = null,
    val topCategoryName: String? = null,
    val isTopCategoryDropDownOpen: Boolean = false,
    val isAddImageDialogOpen:Boolean=false
)
