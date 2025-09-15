package com.techcult.salesman.feature.auth.presentation.UserLogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcult.salesman.core.domain.onError
import com.techcult.salesman.core.domain.onSuccess
import com.techcult.salesman.core.utils.errorToMessage
import com.techcult.salesman.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserLoginViewModel(val authRepository: AuthRepository) : ViewModel() {


    private val _state = MutableStateFlow(UserLoginUiState())
    val state = _state.asStateFlow()

    private val loginChannel = Channel<UserLoginEvents>()
    val loginChannelFlow = loginChannel.receiveAsFlow()


    fun onAction(action: UserLoginAction) {
        when (action) {
            UserLoginAction.Login -> {

                viewModelScope.launch {

                    _state.update {

                        it.copy(isLoading = true)
                    }
                  submit()



                }
            }

            is UserLoginAction.PasswordChanged -> {
                _state.update {
                    it.copy(password = action.password,passwordError = null)
                }
            }

            is UserLoginAction.UsernameChanged -> {
                _state.update {
                    it.copy(username = action.username, userNameError = null)
                }
            }

            else -> {}
        }
    }

    private suspend fun submit() {
        _state.update {
            it.copy(isLoading = true)
        }
        if (validate()) {

            authRepository.userLogin(
                userName = _state.value.username,
                password = _state.value.password
            )
                .onSuccess {
                    _state.update {
                        it.copy(isLoading = false)
                    }
                    loginChannel.send(UserLoginEvents.OnLoginSuccess)

                }
                .onError { error ->
                    _state.update {
                        it.copy(isLoading = false)
                    }

                    loginChannel.send(UserLoginEvents.OnLoginFailed(errorToMessage(error)))


                }

        }
    }

    private fun validate(): Boolean {
        if (_state.value.username.isEmpty()) {
            _state.update {
                it.copy(userNameError = "Username is required",isLoading = false)
            }
            return false

        }
        if (_state.value.password.isEmpty()) {
            _state.update {
                it.copy(passwordError = "Password is required",isLoading = false)
            }
            return false
        }
        if (_state.value.password.length <4) {
            _state.update {
                it.copy(passwordError = "Password must be at least 8 characters",isLoading = false)
            }
            return false
        } else {
            return true

        }
    }

}