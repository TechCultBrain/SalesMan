package com.techcult.salesman.feature.product.presentation.product

sealed interface ProductActions {
    data class AddClick(val addProductDialog: Boolean = true) : ProductActions
    data class AddImageClick(val addImageDialog: Boolean = true) : ProductActions
    data class FilterClick(val filterDialog: Boolean = true) : ProductActions

}