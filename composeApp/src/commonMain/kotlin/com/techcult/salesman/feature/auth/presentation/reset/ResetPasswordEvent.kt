package com.techcult.salesman.feature.auth.presentation.reset

sealed interface ResetPasswordEvent {

    object OnSuccess : ResetPasswordEvent
    data class OnError(val message: String) : ResetPasswordEvent


}