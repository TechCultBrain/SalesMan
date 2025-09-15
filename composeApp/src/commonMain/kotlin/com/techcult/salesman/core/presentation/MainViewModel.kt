package com.techcult.salesman.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcult.salesman.core.data.prefernces.PreferenceManger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(val preferenceManger: PreferenceManger) : ViewModel() {

    private val _state = MutableStateFlow(LoginStatus(isLoggedIn = false, isLoading = true))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            preferenceManger.getAccessToken().collect { token ->

                _state.update {
                    it.copy(isLoggedIn = !token.isNullOrEmpty(), isLoading = false)
                }
            }}
        viewModelScope.launch {
            preferenceManger.getLoggedId().collect { id ->
                _state.update {
                    it.copy(isUserLoggedIn = !id.isNullOrEmpty(), isLoading = false)
                }
            }
        }

    }


    fun logoutUser() {
        viewModelScope.launch {
            preferenceManger.saveAccessToken("")
        }

    }
}

data class LoginStatus(
    val isLoggedIn: Boolean,
    val isLoading: Boolean,
    val isUserLoggedIn: Boolean = false
)