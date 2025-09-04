package com.techcult.salesman.feature.auth.presentation.reset

sealed interface ResetPasswordAction {

    data class OnNewPasswordChange(val password: String): ResetPasswordAction
    data class OnConfirmPasswordChange(val password: String): ResetPasswordAction
    object OnCreatePassword: ResetPasswordAction
    object BackToLogin: ResetPasswordAction
    data class ShowSuccessMessage(val isVisible: Boolean): ResetPasswordAction
}