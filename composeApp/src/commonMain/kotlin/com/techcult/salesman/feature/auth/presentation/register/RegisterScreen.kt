package com.techcult.salesman.feature.auth.presentation.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techcult.salesman.core.presentation.components.LoadingIndicator
import com.techcult.salesman.core.presentation.components.MyButton
import com.techcult.salesman.core.presentation.components.MyTextField
import com.techcult.salesman.core.presentation.components.TextWithClick
import com.techcult.salesman.core.presentation.theme.LocalDimensions
import com.techcult.salesman.core.presentation.theme.LocalPadding
import com.techcult.salesman.core.utils.DeviceConfiguration
import com.techcult.salesman.feature.auth.presentation.component.TermsDialog
import com.techcult.salesman.feature.auth.presentation.login.WelcomeImage
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import salesman.composeapp.generated.resources.Res
import salesman.composeapp.generated.resources.compose_multiplatform

@Composable
fun RegisterScreen(
    onLoginClick: () -> Unit,
    onSuccess: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {


    val state by viewModel.state.collectAsStateWithLifecycle()
    RegisterScreenContent(state, onLoginClick, viewModel::onAction)

}


@Composable
fun RegisterScreenContent(
    state: RegisterState,
    onLoginClick: () -> Unit,
    onAction: (RegisterAction) -> Unit,
    onTermsClick: () -> Unit = {}
) {

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)

    when (deviceConfiguration) {

        DeviceConfiguration.DESKTOP -> {

            RegisterScreenUi(state, onLoginClick, onAction, true)

        }

        else -> {
            RegisterScreenUi(state, onLoginClick, onAction, false)

        }
    }


}


@Composable
fun RegisterScreenUi(
    state: RegisterState,
    onLoginClick: () -> Unit,
    onAction: (RegisterAction) -> Unit,
    isWide: Boolean = true
) {
    val scrollState = rememberScrollState()

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.onPrimary) {
        Row(
            modifier = Modifier.fillMaxSize().padding(
                horizontal = LocalPadding.current.none,
                vertical = LocalPadding.current.none
            ),
        ) {

            if (isWide) {
                WelcomeImage(modifier = Modifier.weight(1.5f))
            }

            Surface(
                modifier = Modifier.weight(1f).padding(if (isWide) LocalPadding.current.large else LocalPadding.current.none)
                    .fillMaxSize(),
                color =  MaterialTheme.colorScheme.onPrimary
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Surface(
                        shape =  RoundedCornerShape(0.dp),
                        modifier = Modifier.padding(0.dp),
                        shadowElevation = 0.dp,
                        color = MaterialTheme.colorScheme.onPrimary,
                    ) {
                        RegisterForm(
                            state = state,
                            onLoginClick = onLoginClick,
                            onAction = onAction
                        )
                    }

                    if (state.isLoading) {
                        LoadingIndicator()
                    }
                    TermsDialog(onDismiss = {
                        onAction(RegisterAction.OnTermsClick(false))
                    }, isTermsDialogOpen = state.isTermDialogOpen)
                }
            }


        }


    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterForm(
    modifier: Modifier = Modifier,
    state: RegisterState,
    onLoginClick: () -> Unit,
    onAction: (RegisterAction) -> Unit,
) {


    Column(
        modifier = Modifier.padding(
            start = LocalPadding.current.large,
            top = LocalPadding.current.large,
            end = LocalPadding.current.large,
            bottom = LocalPadding.current.large
        ).verticalScroll(
            state = rememberScrollState(),
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(Res.drawable.compose_multiplatform),
            contentDescription = null,
            modifier = Modifier.size(LocalDimensions.current.viewLarge)
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.tiny))
        Text(text = "Register", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.size(LocalPadding.current.tiny))
        Text(
            text = "Enter your details to register",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.large))
        MyTextField(
            modifier = Modifier,
            label = "Business Name",
            value = state.companyName,
            onValueChange = {
                onAction(RegisterAction.OnCompanyNameChange(it))
            },
            leadingIcon = Icons.Outlined.Business,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.tiny))
        ExposedDropdownMenuBox(
            modifier = Modifier,
            expanded = state.isDropDownExpanded,
            onExpandedChange = {
                onAction(RegisterAction.OnDropDownSelected(it))
            }
        )
        {
            MyTextField(
                readOnly = true,
                modifier = Modifier,
                label = "Business Type",
                value = state.companyType,
                onValueChange = {
                    onAction(RegisterAction.OnCompanyTypeSelected(it))
                },
                leadingIcon = Icons.Outlined.Business,
                trailingIcon = Icons.Outlined.ArrowDropDown,
                onTrailingIconClick = {
                    onAction(RegisterAction.OnDropDownSelected(true))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            ExposedDropdownMenu(expanded = state.isDropDownExpanded, onDismissRequest = {
                onAction(RegisterAction.OnDropDownSelected(false))
            }) {
                state.typeList?.forEach { typeName ->
                    DropdownMenuItem(text = {
                        Text(text = typeName)
                    }, onClick = {
                        onAction(RegisterAction.OnCompanyTypeSelected(typeName))
                    })
                }
            }

        }
        Spacer(modifier = Modifier.size(LocalPadding.current.tiny))
        MyTextField(
            modifier = Modifier,
            label = "Mobile Number",
            value = state.phoneNumber,
            onValueChange = {
                onAction(RegisterAction.OnPhoneNumberChange(it))
            },
            leadingIcon = Icons.Outlined.Phone,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.tiny))
        MyTextField(
            modifier = Modifier,
            label = "E-Mail",
            value = state.email,
            onValueChange = {
                onAction(RegisterAction.OnEmailChange(it))
            },
            leadingIcon = Icons.Outlined.Email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.tiny))
        MyTextField(
            modifier = Modifier,
            label = "Password",
            value = state.password,
            onValueChange = {
                onAction(RegisterAction.OnPasswordChange(it))
            },
            leadingIcon = Icons.Outlined.Lock,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isPassword = true
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.tiny))
        Row(
            modifier = Modifier.widthIn(
                min = LocalDimensions.current.minWidthForRow,
                max = LocalDimensions.current.maxWidthForRow
            ),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = state.isTermsChecked, onCheckedChange = {
                onAction(RegisterAction.OnCheckChanged(it))
            })
            TextWithClick("I accept", "Terms and Conditions") {
                onAction(RegisterAction.OnTermsClick(true))
            }

        }
        Spacer(modifier = Modifier.size(LocalPadding.current.large))
        MyButton(
            modifier = Modifier.widthIn(
                min = LocalDimensions.current.minWidthForButton,
                max = LocalDimensions.current.maxWidthForButton
            ),
            buttonText = "Register",
            onClick = {
                onAction(RegisterAction.OnRegisterClick)

            },
            isEnabled = true
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.large))
        TextWithClick(firstText = "Already have an account?", secondText = "Sign In") {
            onLoginClick()
        }
    }
}

