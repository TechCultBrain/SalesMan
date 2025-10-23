package com.techcult.salesman.feature.product.presentation.uom

sealed interface UomEvents {
    object SaveUomSuccess : UomEvents
    object UpdateUomSuccess : UomEvents
    data class SaveUomError(val message: String) : UomEvents
    data class UpdateUomError(val message: String) : UomEvents

}