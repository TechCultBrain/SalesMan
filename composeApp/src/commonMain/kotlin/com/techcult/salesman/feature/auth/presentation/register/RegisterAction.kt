package com.techcult.salesman.feature.auth.presentation.register

interface RegisterAction {

    data class OnCompanyNameChange(val name: String): RegisterAction
    data class OnDropDownSelected(val isDropDownSelected: Boolean): RegisterAction
    data class OnCompanyTypeSelected(val type: String): RegisterAction
    data class OnEmailChange(val email: String): RegisterAction
    data class OnPasswordChange(val password: String): RegisterAction
    data class OnPhoneNumberChange(val phoneNumber: String): RegisterAction
    object OnRegisterClick: RegisterAction
    data class OnCheckChanged(val isChecked: Boolean): RegisterAction
    data class OnTermsClick(val isDialogOpen: Boolean): RegisterAction
}