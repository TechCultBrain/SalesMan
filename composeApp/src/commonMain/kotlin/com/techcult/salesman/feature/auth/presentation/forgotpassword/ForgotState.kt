package com.techcult.salesman.feature.auth.presentation.forgotpassword

data class ForgotPasswordState(
    val emailId: String = "",
    val emailError: String? = null,
    val isLoading: Boolean = false
)
