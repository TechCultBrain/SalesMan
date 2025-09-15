package com.techcult.salesman.feature.Settings.store.presentation

sealed interface StoreInfoActions {
    data class OnBusinessNameChange(val businessName: String) : StoreInfoActions
    data class OnBusinessTypeChange(val businessType: String) : StoreInfoActions
    data class OnBusinessAddressChange(val businessAddress: String) : StoreInfoActions
    data class OnBusinessEmailChange(val businessEmail: String) : StoreInfoActions
    data class OnBusinessPhoneChange(val businessPhone: String) : StoreInfoActions
    data class OnBusinessAlternatePhoneChange(val businessAlternatePhone: String) : StoreInfoActions
    data class OnBusinessLocationChange(val businessLocation: String) : StoreInfoActions
    data class OnBusinessCityChange(val businessCity: String) : StoreInfoActions
    data class OnBusinessStateChange(val businessState: String) : StoreInfoActions
    data class OnBusinessZipCodeChange(val businessZipCode: String) : StoreInfoActions
    data class OnBusinessLogoChange(val businessLogo: String) : StoreInfoActions
    data class OnGstNumberChange(val gstNumber: String) : StoreInfoActions
    data class OnPanNumberChange(val panNumber: String) : StoreInfoActions
    data class OnTinNumberChange(val tinNumber: String) : StoreInfoActions
    data class OnFssaiNumberChange(val fssaiNumber: String) : StoreInfoActions
    data class OnBusinessTypeSelected(val typeName: String) : StoreInfoActions
    data object OnSaveClick : StoreInfoActions
    data object OnResetClick : StoreInfoActions

    data class OnBusinessTypeExpanded(val isExpanded: Boolean) : StoreInfoActions


}