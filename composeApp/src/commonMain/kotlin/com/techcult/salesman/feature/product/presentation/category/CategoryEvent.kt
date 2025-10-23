package com.techcult.salesman.feature.product.presentation.category

sealed interface CategoryEvent {
    data class SaveCategoryError(val message: String) : CategoryEvent
    object SaveCategorySuccess : CategoryEvent
    data class UpdateCategoryError(val message: String) : CategoryEvent
    object UpdateCategorySuccess : CategoryEvent

}