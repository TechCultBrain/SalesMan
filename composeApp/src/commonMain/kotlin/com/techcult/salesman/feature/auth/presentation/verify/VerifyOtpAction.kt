package com.techcult.salesman.feature.auth.presentation.verify

sealed interface OtpAction {

    data class OnEnterNumber(val number: Int?, val index: Int): OtpAction
    data class OnChangeFieldFocused(val index: Int): OtpAction
    data object OnKeyboardBack: OtpAction
    object VerifyOtp: OtpAction
    data class OnOtpChange(val otp: String): OtpAction
    object ResendOtp: OtpAction
}