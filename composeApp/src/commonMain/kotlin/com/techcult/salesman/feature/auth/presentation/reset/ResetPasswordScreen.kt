package com.techcult.salesman.feature.auth.presentation.reset

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.techcult.salesman.core.presentation.components.MyButton
import com.techcult.salesman.core.presentation.components.MyTextField
import com.techcult.salesman.core.presentation.components.ObserveAsEvents
import com.techcult.salesman.core.presentation.theme.LocalDimensions
import com.techcult.salesman.core.presentation.theme.LocalPadding
import com.techcult.salesman.core.utils.DeviceConfiguration
import com.techcult.salesman.feature.auth.presentation.component.BackToLogin
import com.techcult.salesman.feature.auth.presentation.component.SuccessDialog
import com.techcult.salesman.feature.auth.presentation.login.WelcomeImage
import kotlinx.coroutines.flow.Flow
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import salesman.composeapp.generated.resources.Res
import salesman.composeapp.generated.resources.compose_multiplatform

@Composable
fun ResetPasswordScreen(
    navigateToLogin: () -> Unit,
    viewModel: ResetPasswordViewModel = koinViewModel()
) {


    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.resetChannelFlow) { event ->
        when (event) {
            is ResetPasswordEvent.OnError -> {

            }

            ResetPasswordEvent.OnSuccess -> {

                navigateToLogin()
            }
        }

    }
    ResetPasswordScreenContent(state, action = { action ->
        when (action) {
            is ResetPasswordAction.BackToLogin -> {
            }

            else -> Unit
        }
        viewModel.onAction(action)

    })

}



@Composable
fun ResetPasswordScreenContent(state: ResetPasswordState, action: (ResetPasswordAction) -> Unit) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
    when (deviceConfiguration) {
        DeviceConfiguration.DESKTOP -> {
            ResetPasswordScreenUi(state, action, true)
        }

        else -> {
            ResetPasswordScreenUi(state, action, false)

        }
    }


}


@Composable
fun ResetPasswordScreenUi(
    state: ResetPasswordState,
    onAction: (ResetPasswordAction) -> Unit,
    isWide: Boolean
) {
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
                modifier = Modifier.weight(1f)
                    .padding(if (isWide) LocalPadding.current.large else LocalPadding.current.none)
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.onPrimary
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Surface(
                        shape = RoundedCornerShape(0.dp),
                        modifier = Modifier.padding(0.dp),
                        shadowElevation = 0.dp,
                        color = MaterialTheme.colorScheme.onPrimary,
                    ) {
                        ResetPasswordForm(state, onAction)

                    }
                    SuccessDialog(onDismiss = {
                        onAction(ResetPasswordAction.ShowSuccessMessage(false))

                    }, state.showSuccessMessage, "Password Created Successfully")


                }
            }


        }


    }

}


@Composable
fun ResetPasswordForm(state: ResetPasswordState, onAction: (ResetPasswordAction) -> Unit) {
    Column(
        modifier = Modifier.padding(
            start = LocalPadding.current.large,
            top = LocalPadding.current.large,
            end = LocalPadding.current.large,
            bottom = LocalPadding.current.large
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(Res.drawable.compose_multiplatform),
            contentDescription = null,
            modifier = Modifier.size(LocalDimensions.current.viewLarge)
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.tiny))
        Text(text = "Set new password", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.size(LocalPadding.current.tiny))
        Text(
            text = "Must be at least 8 characters long",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.large))
        MyTextField(
            modifier = Modifier,
            isError = state.newPasswordError != null,
            label = "Password",
            value = state.newPassword,
            onValueChange = {
                onAction(ResetPasswordAction.OnNewPasswordChange(it))
            },
            supportingText = state.newPasswordError,
            leadingIcon = Icons.Outlined.Lock,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.tiny))
        MyTextField(
            modifier = Modifier,
            isError = state.confirmPasswordError != null,

            label = "Confirm Password",
            supportingText = state.confirmPasswordError,
            value = state.confirmPassword,
            onValueChange = {
                onAction(ResetPasswordAction.OnConfirmPasswordChange(it))
            },
            leadingIcon = Icons.Outlined.Lock,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isPassword = true
        )

        Spacer(modifier = Modifier.size(LocalPadding.current.large))
        MyButton(
            isLoading = state.isLoading,
            modifier = Modifier.widthIn(
                min = LocalDimensions.current.minWidthForButton,
                max = LocalDimensions.current.maxWidthForButton
            ),
            buttonText = "Reset Password",
            onClick = {
                onAction(ResetPasswordAction.OnCreatePassword)

            },
            isEnabled = true
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.large))
        BackToLogin {
            onAction(ResetPasswordAction.BackToLogin)
        }


    }
}