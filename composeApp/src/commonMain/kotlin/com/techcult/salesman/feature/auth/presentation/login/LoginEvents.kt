package com.techcult.salesman.feature.auth.presentation.login

interface LoginEvents {
    object OnLoginSuccess : LoginEvents
    data class OnLoginError(val error: String) : LoginEvents

}