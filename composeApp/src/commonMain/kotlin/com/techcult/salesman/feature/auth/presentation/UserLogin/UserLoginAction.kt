package com.techcult.salesman.feature.auth.presentation.UserLogin

sealed interface UserLoginAction {
    data class UsernameChanged(val username: String) : UserLoginAction
    data class PasswordChanged(val password: String) : UserLoginAction
    object Login : UserLoginAction

}