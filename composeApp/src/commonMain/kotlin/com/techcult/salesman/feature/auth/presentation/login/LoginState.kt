package com.techcult.salesman.feature.auth.presentation.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val isSuccessDialogOpen: Boolean=false,
    val errorMessage: String? = null
)
