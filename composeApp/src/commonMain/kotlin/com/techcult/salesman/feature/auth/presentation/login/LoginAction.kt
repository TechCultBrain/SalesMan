package com.techcult.salesman.feature.auth.presentation.login

interface LoginAction {

   object OnClickLogin: LoginAction
    data class OnEmailChange(val email: String): LoginAction
    data class OnPasswordChange(val password: String): LoginAction
    object OnForgotPasswordClick: LoginAction
    object OnSignUpClick: LoginAction
    data class OnSuccessDialogDismiss(val isDismissed: Boolean): LoginAction

}