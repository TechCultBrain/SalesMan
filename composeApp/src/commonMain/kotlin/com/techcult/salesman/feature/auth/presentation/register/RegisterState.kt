package com.techcult.salesman.feature.auth.presentation.register

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val companyName: String = "",
    val companyType: String = "",
    val phoneNumber: String = "",
    val typeList: List<String>? = null,
    val isLoading: Boolean = false,
    val isDropDownExpanded: Boolean = false,
    val isTermsChecked: Boolean = false,
    val isTermDialogOpen: Boolean=false
)
