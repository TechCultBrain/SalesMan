package com.techcult.salesman.feature.auth.presentation.forgotpassword

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ForgotPasswordViewModel : ViewModel() {


    private val _state = MutableStateFlow(ForgotPasswordState())
    val state = _state.asStateFlow()


    fun onAction(actions: ForgotActions) {
        when (actions) {
            is ForgotActions.OnEmailChange -> {
                _state.update {
                    it.copy(emailId = actions.email)
                }
            }

            is ForgotActions.OnSendOtp -> {
                _state.update {
                    it.copy(isLoading = true)
                }
            }
        }
    }
}