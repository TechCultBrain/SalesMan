package com.techcult.salesman.feature.auth.presentation.UserLogin

data class UserLoginUiState(
    val isLoading: Boolean = false,
    val username: String = "",
    val password: String = "",
    val userNameError: String? = null,
    val passwordError: String? = null,
    val isLoginSuccess: Boolean = false
)
