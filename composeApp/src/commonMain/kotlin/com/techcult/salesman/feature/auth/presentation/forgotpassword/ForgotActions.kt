package com.techcult.salesman.feature.auth.presentation.forgotpassword

sealed interface ForgotActions {

    data class OnEmailChange(val email: String) : ForgotActions
    object OnSendOtp : ForgotActions
}