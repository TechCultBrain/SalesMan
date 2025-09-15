package com.techcult.salesman.feature.auth.presentation.UserLogin

sealed interface UserLoginEvents {
    object OnLoginSuccess : UserLoginEvents
    data class OnLoginFailed(val message: String) : UserLoginEvents


}