package com.techcult.salesman.feature.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcult.salesman.core.data.prefernces.PreferenceManger
import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.onError
import com.techcult.salesman.core.domain.onSuccess
import com.techcult.salesman.feature.auth.domain.usecase.LoginUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(val loginUseCase: LoginUseCase,val preferenceManger: PreferenceManger) : ViewModel() {


    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            preferenceManger.getAccessToken().let {token->


            }
        }
    }

    private val loginChannel = Channel<LoginEvents>()
    val loginChannelFlow = loginChannel.receiveAsFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnEmailChange -> {
                _state.update {
                    it.copy(email = action.email, emailError = null, passwordError = null)
                }
            }

            is LoginAction.OnPasswordChange -> {
                _state.update {
                    it.copy(password = action.password, emailError = null, passwordError = null)
                }
            }

            is LoginAction.OnClickLogin -> {
                _state.update {
                    it.copy(isLoading = true)
                }
                viewModelScope.launch {
                    loginUseCase.invoke(_state.value.email, _state.value.password)
                        .onSuccess {
                            _state.update {
                                it.copy(isLoading = false, isSuccessDialogOpen = true)
                            }
                            delay(2000)
                            _state.update {
                                it.copy(isLoading = false, isSuccessDialogOpen = false)
                            }
                            loginChannel.send(LoginEvents.OnLoginSuccess)
                        }
                        .onError { error ->
                            _state.update {
                                it.copy(isLoading = false)
                            }
                            when (error) {
                                DataError.NetworkError.SERIALIZATION -> {
                                    loginChannel.send(LoginEvents.OnLoginError("Serialization error"))

                                }

                                DataError.NetworkError.UNKNOWN -> {
                                    loginChannel.send(LoginEvents.OnLoginError("Unknown error"))

                                }

                                else -> {
                                    loginChannel.send(LoginEvents.OnLoginError("Unknown error"))

                                }

                            }


                        }
                }
            }
        }
    }


}