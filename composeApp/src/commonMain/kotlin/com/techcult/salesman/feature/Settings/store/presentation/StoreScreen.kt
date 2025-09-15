package com.techcult.salesman.feature.Settings.store.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techcult.salesman.core.presentation.components.ButtonWithIcon
import com.techcult.salesman.core.presentation.components.HeaderTextWithIcon
import com.techcult.salesman.core.presentation.components.MyTextField
import com.techcult.salesman.core.presentation.components.ObserveAsEvents
import com.techcult.salesman.core.presentation.theme.LocalPadding
import com.techcult.salesman.core.utils.DeviceConfiguration
import com.techcult.salesman.feature.user.presentation.UserActions
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StoreInformationScreen(viewModel: StoreInfoViewModel = koinViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val snackBarState = remember { SnackbarHostState() }
    ObserveAsEvents(viewModel.storeEvents) {
        when (it) {
            is StoreInfoEvents.SaveStoreInfoError -> {
                scope.launch {

                    snackBarState.showSnackbar(it.message)
                }
            }

            StoreInfoEvents.SaveStoreInfoSuccess -> {
                scope.launch {
                    snackBarState.showSnackbar("Store info saved successfully")
                }
            }
            StoreInfoEvents.UpdateStoreInfoSuccess -> {
                scope.launch {
                    snackBarState.showSnackbar("Store info updated successfully")
                }
            }
        }
    }

    StoreInformationScreenContent(state, onAction = viewModel::onAction,snackBarState)

}


@Composable
fun StoreInformationScreenContent(state: StoreUiState, onAction: (StoreInfoActions) -> Unit,snackbarHostState: SnackbarHostState) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
    Scaffold(snackbarHost = {
        SnackbarHost(
        snackbarHostState)
    }, modifier = Modifier, bottomBar = {

    }) { paddingValues ->
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize().padding(LocalPadding.current.normal)
                    .verticalScroll(state = rememberScrollState())
            ) {
                HeaderTextWithIcon(
                    icon = Icons.Outlined.Store,
                    title = "Store Information",
                    subtitle = "Store Information",
                    isAddButton = true, onButtonClicked = {
                        onAction(StoreInfoActions.OnSaveClick)
                    },
                    deviceConfiguration = deviceConfiguration,
                    buttonIcon = Icons.Outlined.Save,
                    buttonText = if (state.isEditMode) "Update" else "Save"
                )
                BasicInformationForm(state, onAction)
                AddressInformationForm(state, onAction)
                ContactInformationForm(state, onAction)
                RegistrationInformationForm(state, onAction)
                Spacer(modifier = Modifier.height(LocalPadding.current.normal))


            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicInformationForm(state: StoreUiState, action: (StoreInfoActions) -> Unit) {
    Column(modifier = Modifier.padding(LocalPadding.current.normal)) {
        Text(text = "Basic Information")
        Spacer(modifier = Modifier.height(LocalPadding.current.normal))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.tiny),
            verticalArrangement = Arrangement.spacedBy(LocalPadding.current.tiny),
            maxItemsInEachRow = 2

        ) {
            MyTextField(
                modifier = Modifier.weight(1f),
                label = "Business Name*",
                placeholder = "Business Name",
                value = state.businessName,
                isError = state.businessNameError != null,
                supportingText = state.businessNameError,
                onValueChange = {
                    action(StoreInfoActions.OnBusinessNameChange(it))
                })
            ExposedDropdownMenuBox(expanded = state.isBusinessTypeExpanded, onExpandedChange = {
                action(StoreInfoActions.OnBusinessTypeExpanded(it))
            }) {
                MyTextField(
                    label = "Business Type*",

                    placeholder = "Business Type",
                    modifier = Modifier.weight(1f),
                    value = state.businessType,
                    trailingIcon = Icons.Default.ArrowDropDown,
                    onTrailingIconClick = {
                        if (state.isBusinessTypeExpanded) {
                            action(StoreInfoActions.OnBusinessTypeExpanded(false))
                        } else {
                            action(StoreInfoActions.OnBusinessTypeExpanded(true))
                        }

                    },
                    readOnly = true,
                    onValueChange = {
                        action(StoreInfoActions.OnBusinessTypeChange(it))
                    })
                ExposedDropdownMenu(expanded = state.isBusinessTypeExpanded, onDismissRequest = {
                    action(StoreInfoActions.OnBusinessTypeExpanded(false))
                }) {
                    state.businessTypeList.forEach {
                        Text(text = it.name, modifier = Modifier.fillMaxWidth().clickable{
                            action(StoreInfoActions.OnBusinessTypeSelected(it.name))
                        }.padding(LocalPadding.current.micro))
                    }
                }
            }

            MyTextField(
                modifier = Modifier.weight(1f),
                value = state.businessLocation,
                label = "Business Location*",
                isError = state.businessLocationError != null,
                supportingText = state.businessLocationError,

                placeholder = "Business Location",
                onValueChange = {
                    action(StoreInfoActions.OnBusinessLocationChange(it))
                })


        }
    }

}

@Composable
fun AddressInformationForm(state: StoreUiState, action: (StoreInfoActions) -> Unit) {
    Column(modifier = Modifier.padding(LocalPadding.current.normal)) {
        Text(text = "Address Information")
        Spacer(modifier = Modifier.height(LocalPadding.current.normal))
        MyTextField(
            modifier = Modifier.fillMaxWidth().height(120.dp),
            label = "Business Address*",
            placeholder = "Business Address",
            value = state.businessAddress,
            maxLines = 3,
            isError = state.businessAddressError != null,
            supportingText = state.businessAddressError,
            singleLine = false,
            onValueChange = {
                action(StoreInfoActions.OnBusinessAddressChange(it))
            })
        Spacer(modifier = Modifier.height(LocalPadding.current.tiny))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.tiny),
            verticalArrangement = Arrangement.spacedBy(LocalPadding.current.tiny),
            maxItemsInEachRow = 3
        ) {
            MyTextField(
                modifier = Modifier.weight(1f),
                label = "Business City*",
                placeholder = "Business City",
                value = state.businessCity,
                isError = state.businessCityError != null,
                supportingText = state.businessCityError,

                onValueChange = {
                    action(StoreInfoActions.OnBusinessCityChange(it))
                })
            MyTextField(
                modifier = Modifier.weight(1f),
                label = "Business State*",
                isError = state.businessStateError != null,
                supportingText = state.businessStateError,

                placeholder = "Business State",
                value = state.businessState,
                onValueChange = {
                    action(StoreInfoActions.OnBusinessStateChange(it))
                })
            

            MyTextField(
                modifier = Modifier.weight(1f),
                label = "Business Zip Code*",
                isError = state.businessZipCodeError != null,
                supportingText = state.businessZipCodeError,
                placeholder = "Business Zip Code",
                value = state.businessZipCode,
                onValueChange = {
                    action(StoreInfoActions.OnBusinessZipCodeChange(it))
                })

        }

    }

}

@Composable
fun ContactInformationForm(state: StoreUiState, action: (StoreInfoActions) -> Unit) {
    Column(modifier = Modifier.padding(LocalPadding.current.normal)) {
        Text(text = "Contact Information")
        Spacer(modifier = Modifier.height(LocalPadding.current.normal))
        FlowRow(
            maxItemsInEachRow = 2,
            verticalArrangement = Arrangement.spacedBy(LocalPadding.current.tiny),
            horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.tiny)
        ) {
            MyTextField(
                modifier = Modifier.weight(1f),
                label = "Business Phone*",
                placeholder = "Business Phone",
                value = state.businessPhone,
                isError = state.businessPhoneError != null,
                supportingText = state.businessPhoneError,
                onValueChange = {
                    action(StoreInfoActions.OnBusinessPhoneChange(it))
                })
            MyTextField(
                modifier = Modifier.weight(1f),
                label = "Business Alternate Phone",
                placeholder = "Business Alternate Phone",
                isError = state.businessAlternatePhoneError != null,
                supportingText = state.businessAlternatePhoneError,
                value = state.businessAlternatePhone,
                onValueChange = {
                    action(StoreInfoActions.OnBusinessAlternatePhoneChange(it))
                })



            MyTextField(
                modifier = Modifier.weight(1f),
                label = "Business Email*",
                placeholder = "Business Email",
                value = state.businessEmail,
                isError = state.businessEmailError != null,
                supportingText = state.businessEmailError,

                onValueChange = {
                    action(StoreInfoActions.OnBusinessEmailChange(it))

                })
        }


    }

}

@Composable
fun RegistrationInformationForm(state: StoreUiState, action: (StoreInfoActions) -> Unit) {
    Column(modifier = Modifier.padding(LocalPadding.current.normal)) {
        Text(text = "Registration & Legal Details")
        Spacer(modifier = Modifier.height(LocalPadding.current.normal))
        FlowRow(
            maxItemsInEachRow = 2,
            verticalArrangement = Arrangement.spacedBy(LocalPadding.current.tiny),
            horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.tiny)
        ) {
            MyTextField(
                modifier = Modifier.weight(1f),
                label = "GST Number",
                placeholder = "GST Number",
                value = state.gstNumber,
                isError = state.gstNumberError != null,


                onValueChange = {
                    action(StoreInfoActions.OnGstNumberChange(it))
                },
                supportingText = state.gstNumberError ?: "15-Character GST identification number"
            )
            MyTextField(
                modifier = Modifier.weight(1f),
                label = "TIN Number",
                placeholder = "TIN Number",
                value = state.tinNumber,
                onValueChange = {
                    action(StoreInfoActions.OnTinNumberChange(it))
                },
                supportingText = state.tinNumberError ?: "Tax Identification Number"
            )
            MyTextField(
                modifier = Modifier.weight(1f),
                label = "FSSAI Number",
                placeholder = "Fssai Number",
                value = state.fssaiNumber,
                onValueChange = {
                    action(StoreInfoActions.OnFssaiNumberChange(it))
                },
                supportingText = state.fssaiNumberError
                    ?: "Food safety and Standards Authority of India license number(for food business")


        }

    }

}