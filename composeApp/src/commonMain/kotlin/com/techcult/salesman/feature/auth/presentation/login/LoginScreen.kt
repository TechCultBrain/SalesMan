package com.techcult.salesman.feature.auth.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techcult.salesman.core.presentation.components.MyButton
import com.techcult.salesman.core.presentation.components.MyTextField
import com.techcult.salesman.core.presentation.components.ObserveAsEvents
import com.techcult.salesman.core.presentation.components.TextWithClick
import com.techcult.salesman.core.presentation.theme.LocalDimensions
import com.techcult.salesman.core.presentation.theme.LocalPadding
import com.techcult.salesman.core.utils.DeviceConfiguration
import com.techcult.salesman.feature.auth.presentation.component.ErrorMessage
import com.techcult.salesman.feature.auth.presentation.component.SuccessDialog
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import salesman.composeapp.generated.resources.Res
import salesman.composeapp.generated.resources.compose_multiplatform
import salesman.composeapp.generated.resources.welcomeimage

@Composable
fun LoginScreen(
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    viewModel: LoginViewModel = koinViewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.loginChannelFlow) { event ->
        when (event) {
            is LoginEvents.OnLoginSuccess -> {
                navigateToHome()
            }

            is LoginEvents.OnLoginError -> {
                scope.launch {
                    snackbarHostState.showSnackbar(event.error)
                }

            }

        }

    }


    Scaffold(snackbarHost = {

        SnackbarHost(snackbarHostState)

    }) {
        LoginScreenContent(
            state,
            onAction = { action ->
                when (action) {
                    is LoginAction.OnSignUpClick -> {
                        navigateToSignUp()
                    }

                    is LoginAction.OnForgotPasswordClick -> {
                        navigateToForgotPassword()
                    }
                }
                viewModel.onAction(action)
            },
        )
    }
}

@Preview
@Composable
fun LoginScreenContent(
    state: LoginState, onAction: (LoginAction) -> Unit, modifier: Modifier = Modifier
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)


    when (deviceConfiguration) {
        DeviceConfiguration.DESKTOP -> {

            LoginScreenUi(state, modifier, onAction, true)


        }

        else -> {

            LoginScreenUi(state, modifier, onAction, false)


        }
    }

}


@Composable
fun LoginScreenUi(
    state: LoginState,
    modifier: Modifier = Modifier,
    onAction: (LoginAction) -> Unit,
    isWide: Boolean = true
) {

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.onPrimary) {
        Row(
            modifier = Modifier.fillMaxSize().padding(
                horizontal = LocalPadding.current.none, vertical = LocalPadding.current.none
            ),
        ) {

            if (isWide) {
                WelcomeImage(modifier = Modifier.weight(1.5f))
            }

            Surface(
                modifier = Modifier.weight(1f)
                    .padding(if (isWide) LocalPadding.current.large else LocalPadding.current.none)
                    .fillMaxSize(), color = MaterialTheme.colorScheme.onPrimary
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {

                    Surface(
                        color = MaterialTheme.colorScheme.onPrimary,
                    ) {
                        LoginForm(
                            state, onAction, modifier
                        )
                    }
                    SuccessDialog(onDismiss = {
                        onAction(LoginAction.OnSuccessDialogDismiss(true))
                    }, isDialogOpen = state.isSuccessDialogOpen, message = "Login Successful!")


                }
            }


        }


    }

}


@Composable
fun LoginForm(
    state: LoginState, onAction: (LoginAction) -> Unit, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(
            start = LocalPadding.current.large,
            top = LocalPadding.current.large,
            end = LocalPadding.current.large,
            bottom = LocalPadding.current.large
        ), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(Res.drawable.compose_multiplatform),
            contentDescription = null,
            modifier = Modifier.size(LocalDimensions.current.viewLarge)
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.tiny))
        Text(text = "Login", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.size(LocalPadding.current.tiny))
        Text(
            text = "Enter your credentials to continue",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.large))
        MyTextField(
            isError = state.emailError != null,
            modifier = Modifier,
            label = "Email",
            value = state.email,
            supportingText = state.emailError,
            onValueChange = {
                onAction(LoginAction.OnEmailChange(it))
            },
            leadingIcon = Icons.Outlined.Email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.tiny))
        MyTextField(
            isError = state.passwordError != null,
            modifier = Modifier,
            label = "Password",
            supportingText = state.passwordError,
            value = state.password,
            onValueChange = {
                onAction(LoginAction.OnPasswordChange(it))
            },
            leadingIcon = Icons.Outlined.Lock,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isPassword = true
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.large))
        Row(
            modifier = Modifier.widthIn(
                min = LocalDimensions.current.minWidthForRow,
                max = LocalDimensions.current.maxWidthForRow
            ), horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Forgot Password?",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.CenterVertically).clickable() {
                    onAction(LoginAction.OnForgotPasswordClick)
                })
        }
        Spacer(modifier = Modifier.size(LocalPadding.current.large))
        MyButton(
            isLoading = state.isLoading, modifier = Modifier.widthIn(
                min = LocalDimensions.current.minWidthForButton,
                max = LocalDimensions.current.maxWidthForButton
            ), buttonText = "Login", onClick = {
                onAction(LoginAction.OnClickLogin)

            }, isEnabled = true
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.large))
        TextWithClick(firstText = "Don't have an account?", secondText = "Sign Up") {
            onAction(LoginAction.OnSignUpClick)

        }
//        Spacer(modifier = Modifier.size(LocalPadding.current.large))
//        state.errorMessage?.let {
//            ErrorMessage(message = it) { }
//        }
    }
}

@Composable
fun WelcomeImage(modifier: Modifier = Modifier) {

    Surface(
        modifier = modifier.fillMaxSize().padding(
            start = LocalPadding.current.extraLarge,
            top = LocalPadding.current.extraLarge,
            bottom = LocalPadding.current.extraLarge
        ), shape = MaterialTheme.shapes.medium

    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(Res.drawable.welcomeimage),
                contentDescription = null,
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                alignment = Alignment.Center
            )
            Text(
                "Welcome to our App!",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.BottomCenter),
                color = Color.White
            )
        }

    }
}


