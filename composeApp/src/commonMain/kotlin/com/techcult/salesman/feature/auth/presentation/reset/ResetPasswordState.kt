package com.techcult.salesman.feature.auth.presentation.reset

data class ResetPasswordState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val newPassword: String = "",
    val newPasswordError: String? = null,
    val confirmPasswordError: String? = null,
    val confirmPassword: String = "",
    val showSuccessMessage: Boolean = false

)
