package com.techcult.salesman.feature.product.presentation.brand

sealed interface BrandEvent {
    data class SaveBrandError(val message: String) : BrandEvent
    object SaveBrandSuccess : BrandEvent
    data class UpdateBrandError(val message: String) : BrandEvent
    object UpdateBrandSuccess : BrandEvent
}