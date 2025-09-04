package com.techcult.salesman.feature.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcult.salesman.core.data.BusinessType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {


    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    typeList = BusinessType.entries.map { type -> type.typeName },
                    companyType = BusinessType.entries.first().typeName
                )
            }
        }
    }

    fun onAction(actions: RegisterAction) {
        when (actions) {
            is RegisterAction.OnCompanyNameChange -> {
                _state.update {
                    it.copy(companyName = actions.name)
                }
            }

            is RegisterAction.OnCompanyTypeSelected -> {
                _state.update {
                    it.copy(companyType = actions.type, isDropDownExpanded = false)
                }
            }

            is RegisterAction.OnEmailChange -> {
                _state.update {
                    it.copy(email = actions.email)
                }
            }

            is RegisterAction.OnPasswordChange -> {
                _state.update {
                    it.copy(password = actions.password)


                }
            }

            is RegisterAction.OnPhoneNumberChange -> {
                _state.update {
                    it.copy(phoneNumber = actions.phoneNumber)
                }
            }

            is RegisterAction.OnDropDownSelected -> {
                _state.update {
                    it.copy(isDropDownExpanded = actions.isDropDownSelected)
                }
            }

            is RegisterAction.OnRegisterClick -> {
                _state.update {
                    it.copy(isLoading = true)
                }

            }

            is RegisterAction.OnCheckChanged -> {
                _state.update {
                    it.copy(isTermsChecked = actions.isChecked)
                }
            }
            is RegisterAction.OnTermsClick->
            {
                _state.update {
                    it.copy(isTermDialogOpen = actions.isDialogOpen)
                }
            }
        }
    }


}