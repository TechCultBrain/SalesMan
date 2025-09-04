package com.techcult.salesman.feature.auth.presentation.reset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.onError
import com.techcult.salesman.core.domain.onSuccess
import com.techcult.salesman.feature.auth.domain.usecase.ResetPasswordUseCase
import com.techcult.salesman.feature.auth.presentation.reset.ResetPasswordAction.OnNewPasswordChange
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ResetPasswordViewModel(val useCase: ResetPasswordUseCase) : ViewModel() {

    private val _state = MutableStateFlow(ResetPasswordState())
    val state = _state.asStateFlow()

    private val resetChannel = Channel<ResetPasswordEvent>()
    val resetChannelFlow = resetChannel.receiveAsFlow()


    fun onAction(action: ResetPasswordAction) {
        when (action) {
            is ResetPasswordAction.OnConfirmPasswordChange -> {
                _state.update {
                    it.copy(
                        confirmPassword = action.password,
                        confirmPasswordError = null,
                        newPasswordError = null
                    )
                }

            }

            is OnNewPasswordChange -> {
                _state.update {
                    it.copy(
                        newPassword = action.password,
                        newPasswordError = null,
                        confirmPasswordError = null
                    )
                }
            }

            ResetPasswordAction.OnCreatePassword -> {
                _state.update {
                    it.copy(isLoading = true)
                }
                viewModelScope.launch {
                    delay(5000)
                    useCase.execute(_state.value.newPassword, _state.value.confirmPassword)
                        .onSuccess {
                            _state.update {
                                it.copy(isLoading = false, showSuccessMessage = true)
                            }
                            delay(5000)
                            _state.update {
                                it.copy(isLoading = false, showSuccessMessage = false)
                            }
                            resetChannel.send(ResetPasswordEvent.OnSuccess)

                        }.onError {
                            when (it) {
                                DataError.Validation.INVALID_PASSWORD -> {
                                    _state.update {
                                        it.copy(
                                            isLoading = false, newPasswordError = "Invalid Password"
                                        )
                                    }

                                }

                                DataError.Validation.PASSWORD_MISMATCH -> {
                                    _state.update {
                                        it.copy(
                                            isLoading = false,
                                            confirmPasswordError = "Password Mismatch"
                                        )
                                    }

                                }

                                DataError.Validation.PASSWORD_TOO_SHORT -> {
                                    _state.update {
                                        it.copy(
                                            isLoading = false,
                                            newPasswordError = "Password TooShort"
                                        )
                                    }

                                }


                                else -> {
                                    resetChannel.send(ResetPasswordEvent.OnError("Error Occurred"))

                                }
                            }
                        }

                }


            }

            else -> {}
        }
    }


}