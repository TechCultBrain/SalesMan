package com.techcult.salesman.feature.Settings.store.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcult.salesman.core.domain.onError
import com.techcult.salesman.core.domain.onSuccess
import com.techcult.salesman.core.utils.errorToMessage
import com.techcult.salesman.feature.Settings.store.domain.StoreInfo
import com.techcult.salesman.feature.Settings.store.domain.StoreRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StoreInfoViewModel(val storeRepository: StoreRepository) : ViewModel() {

    private val _state = MutableStateFlow(StoreUiState())
    val state = _state.asStateFlow()

    init {

        viewModelScope.launch {
            storeRepository.checkStoreInfo().onSuccess {count->
                if (count == 0) {
                    _state.update {
                        it.copy(isEditMode = false)
                    }
                }
                else{
                    _state.update {
                        it.copy(isEditMode = true)
                    }
                }

            }

        }
        viewModelScope.launch {

            getStoreInfo()
        }

    }


    private val storeChannel = Channel<StoreInfoEvents>()
    val storeEvents = storeChannel.receiveAsFlow()

    fun onAction(action: StoreInfoActions) {
        when (action) {
            is StoreInfoActions.OnBusinessNameChange -> {
                _state.update {
                    it.copy(businessName = action.businessName, businessNameError = null)
                }
            }

            is StoreInfoActions.OnBusinessAddressChange -> {
                _state.update {
                    it.copy(businessAddress = action.businessAddress, businessAddressError = null)
                }

            }

            is StoreInfoActions.OnBusinessAlternatePhoneChange -> {
                _state.update {
                    it.copy(
                        businessAlternatePhone = action.businessAlternatePhone,
                        businessAlternatePhoneError = null
                    )
                }
            }

            is StoreInfoActions.OnBusinessCityChange -> {
                _state.update {
                    it.copy(businessCity = action.businessCity, businessCityError = null)
                }
            }

            is StoreInfoActions.OnBusinessEmailChange -> {
                _state.update {
                    it.copy(businessEmail = action.businessEmail, businessEmailError = null)
                }
            }

            is StoreInfoActions.OnBusinessLocationChange -> {
                _state.update {
                    it.copy(
                        businessLocation = action.businessLocation,
                        businessLocationError = null
                    )
                }
            }

            is StoreInfoActions.OnBusinessLogoChange -> TODO()
            is StoreInfoActions.OnBusinessPhoneChange -> {
                _state.update {
                    it.copy(businessPhone = action.businessPhone, businessPhoneError = null)
                }
            }

            is StoreInfoActions.OnBusinessStateChange -> {
                _state.update {
                    it.copy(businessState = action.businessState, businessStateError = null)
                }
            }

            is StoreInfoActions.OnBusinessTypeChange -> {
                _state.update {
                    it.copy(businessType = action.businessType)
                }
            }

            is StoreInfoActions.OnBusinessTypeSelected -> {
                _state.update {
                    it.copy(businessType = action.typeName, isBusinessTypeExpanded = false)
                }
            }

            is StoreInfoActions.OnBusinessZipCodeChange -> {
                _state.update {
                    it.copy(businessZipCode = action.businessZipCode, businessZipCodeError = null)
                }
            }

            is StoreInfoActions.OnFssaiNumberChange -> {
                _state.update {
                    it.copy(fssaiNumber = action.fssaiNumber, fssaiNumberError = null)
                }
            }

            is StoreInfoActions.OnGstNumberChange -> {
                _state.update {
                    it.copy(gstNumber = action.gstNumber, gstNumberError = null)
                }
            }

            is StoreInfoActions.OnPanNumberChange -> {
                _state.update {
                    it.copy(panNumber = action.panNumber, panNumberError = null)
                }
            }

            StoreInfoActions.OnResetClick -> {

            }

            StoreInfoActions.OnSaveClick -> {
                _state.update {
                    it.copy(isLoading = true)

                }
                if (validateStoreFields(_state.value)) {

                    val values = _state.value
                    val storeInfo =
                        StoreInfo(
                            storeId = values.storeId,
                            businessName = values.businessName,
                            businessType = values.businessType,
                            businessLocation = values.businessLocation,
                            businessAddress = values.businessAddress,
                            businessCity = values.businessCity,
                            businessState = values.businessState,
                            businessZipCode = values.businessZipCode,
                            businessEmail = values.businessEmail,
                            businessPhone = values.businessPhone,
                            businessAlternatePhone = values.businessAlternatePhone,
                            businessLogo = values.businessLogo,
                            gstNumber = values.gstNumber,
                            panNumber = values.panNumber,
                            tinNumber = values.tinNumber,
                            fssaiNumber = values.fssaiNumber
                        )


                    viewModelScope.launch {
                        if (_state.value.isEditMode) {
                            storeRepository.updateStoreInfo(storeInfo).onSuccess {
                                _state.update {
                                    it.copy(isLoading = false)
                                }
                                storeChannel.send(StoreInfoEvents.UpdateStoreInfoSuccess)
                            }
                                .onError { error ->
                                    _state.update {
                                        it.copy(isLoading = false)
                                    }
                                    storeChannel.send(
                                        StoreInfoEvents.SaveStoreInfoError(
                                            errorToMessage(error)
                                        )
                                    )
                                }
                        } else {


                            storeRepository.saveStoreInfo(storeInfo).onSuccess {
                                _state.update {
                                    it.copy(isLoading = false)
                                }
                                storeChannel.send(StoreInfoEvents.SaveStoreInfoSuccess)
                            }
                                .onError {
                                    _state.update {
                                        it.copy(isLoading = false)
                                    }
                                    storeChannel.send(
                                        StoreInfoEvents.SaveStoreInfoError(
                                            errorToMessage(it)
                                        )
                                    )

                                }
                        }
                    }
                }


            }

            is StoreInfoActions.OnTinNumberChange -> {
                _state.update {
                    it.copy(tinNumber = action.tinNumber)
                }
            }

            is StoreInfoActions.OnBusinessTypeExpanded -> {
                _state.update {
                    it.copy(isBusinessTypeExpanded = action.isExpanded)
                }
            }
        }
    }

    private fun validateStoreFields(value: StoreUiState): Boolean {
        if (value.businessName.isEmpty()) {
            _state.update {
                it.copy(businessNameError = "Business name is required", isLoading = false)
            }
            return false
        }

        if (value.businessLocation.isEmpty()) {
            _state.update {
                it.copy(businessLocationError = "Business location is required", isLoading = false)
            }
            return false
        }
        if (value.businessAddress.isEmpty()) {
            _state.update {
                it.copy(businessAddressError = "Business address is required", isLoading = false)
            }
            return false
        }
        if (value.businessCity.isEmpty()) {
            _state.update {
                it.copy(businessCityError = "Business city is required", isLoading = false)
            }
            return false
        }
        if (value.businessState.isEmpty()) {
            _state.update {
                it.copy(businessStateError = "Business state is required", isLoading = false)
            }
            return false
        }
        if (value.businessZipCode.isEmpty()) {
            _state.update {
                it.copy(businessZipCodeError = "Business zip code is required", isLoading = false)
            }
            return false
        }
        if (value.businessZipCode.length != 6) {
            _state.update {
                it.copy(
                    businessZipCodeError = "Business zip code must be 6 digits",
                    isLoading = false
                )
            }
            return false
        }
        if (value.businessPhone.isEmpty()) {
            _state.update {
                it.copy(businessPhoneError = "Business phone is required", isLoading = false)
            }
            return false
        }
        if (value.businessPhone.length != 10) {
            _state.update {
                it.copy(businessPhoneError = "Business phone must be 10 digits", isLoading = false)
            }
            return false
        }
        if (value.businessAlternatePhone.isEmpty()) {
            _state.update {
                it.copy(
                    businessAlternatePhoneError = "Business alternate phone is required",
                    isLoading = false
                )
            }
            return false
        }
        if (value.businessAlternatePhone.length != 10) {
            _state.update {
                it.copy(
                    businessAlternatePhoneError = "Business alternate phone must be 10 digits",
                    isLoading = false
                )
            }
            return false
        }
        if (value.businessEmail.isEmpty()) {
            _state.update {
                it.copy(businessEmailError = "Business email is required", isLoading = false)
            }
            return false
        }
        if (!value.businessEmail.contains("@")) {
            _state.update {
                it.copy(businessEmailError = "Business email is invalid", isLoading = false)
            }
            return false
        }
        if (value.gstNumber.isNotEmpty() && value.gstNumber.length != 15) {
            _state.update {
                it.copy(gstNumberError = "GST number must be 15 digits", isLoading = false)
            }
            return false
        }
        /*  if (value.panNumber.isEmpty()) {
              _state.update {
                  it.copy(panNumberError = "PAN number is required", isLoading = false)
              }
              return false
          }*/
        /* if (value.tinNumber.isEmpty()) {
             _state.update {
                 it.copy(tinNumberError = "TIN number is required", isLoading = false)
             }
             return false
         }*/
        /* if (value.fssaiNumber.isEmpty()) {
             _state.update {
                 it.copy(fssaiNumberError = "FSSAI number is required", isLoading = false)
             }
             return false
         }*/
        return true


    }

    suspend fun getStoreInfo() {
        storeRepository.getStoreInfo().collect { storeInfo ->
            storeInfo ?: return@collect
            _state.update {
                it.copy(
                    storeId = storeInfo.storeId,
                    businessName = storeInfo.businessName,
                    businessType = storeInfo.businessType,
                    businessLocation = storeInfo.businessLocation,
                    businessAddress = storeInfo.businessAddress,
                    businessCity = storeInfo.businessCity,
                    businessState = storeInfo.businessState,
                    businessZipCode = storeInfo.businessZipCode,
                    businessEmail = storeInfo.businessEmail,
                    businessPhone = storeInfo.businessPhone,
                    businessAlternatePhone = storeInfo.businessAlternatePhone,
                    businessLogo = storeInfo.businessLogo,
                    gstNumber = storeInfo.gstNumber,
                    panNumber = storeInfo.panNumber,
                    tinNumber = storeInfo.tinNumber,
                    isLoading = false
                )
            }


        }
    }

}