package com.techcult.salesman.feature.auth.presentation.verify

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VerifyOtpViewModel : ViewModel() {


    private val _state = MutableStateFlow(VerifyOtpState())
    val state = _state.asStateFlow()


    fun onAction(action: OtpAction) {
        when (action) {
            is OtpAction.OnChangeFieldFocused -> TODO()
            is OtpAction.OnEnterNumber -> TODO()
            OtpAction.OnKeyboardBack -> TODO()
            OtpAction.VerifyOtp -> {


            }

            is OtpAction.OnOtpChange -> {
                _state.update {
                    it.copy(otpText = action.otp)
                }
            }

            OtpAction.ResendOtp -> {
                _state.update {
                    it.copy(timeLeft = 60)
                }
            }
        }
    }

    fun reduceTimeLeft() {
        _state.update {
            it.copy(timeLeft = it.timeLeft - 1)
        }
    }


}