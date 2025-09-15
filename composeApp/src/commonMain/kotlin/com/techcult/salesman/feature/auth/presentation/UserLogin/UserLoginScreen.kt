package com.techcult.salesman.feature.auth.presentation.UserLogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techcult.salesman.core.presentation.components.MyButton
import com.techcult.salesman.core.presentation.components.MyTextField
import com.techcult.salesman.core.presentation.components.ObserveAsEvents
import com.techcult.salesman.core.presentation.theme.LocalPadding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import salesman.composeapp.generated.resources.Res
import salesman.composeapp.generated.resources.compose_multiplatform

@Composable
fun UserLoginScreen(onLoginSuccess: () -> Unit, viewModel: UserLoginViewModel = koinViewModel()) {

    val snackbarHostState = remember { SnackbarHostState() }
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()


    ObserveAsEvents(flow = viewModel.loginChannelFlow) { events ->
        when (events) {
            is UserLoginEvents.OnLoginSuccess -> {
                scope.launch {
                    snackbarHostState.showSnackbar("Login Success")
                    delay(3000)
                    onLoginSuccess()
                }
            }

            is UserLoginEvents.OnLoginFailed -> {
                scope.launch {

                    snackbarHostState.showSnackbar(events.message)
                }
            }
        }

    }
    Scaffold(snackbarHost = {
        SnackbarHost(snackbarHostState)
    }) {

        UserLoginContent(state = state, onAction = { action ->

            viewModel.onAction(action)
        })
    }


}


@Composable
fun UserLoginContent(state: UserLoginUiState, onAction: (UserLoginAction) -> Unit) {
    Surface {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = "Logo",
                    modifier = Modifier.size(150.dp)
                )
                Spacer(modifier = Modifier.padding(LocalPadding.current.normal))
                Text(text = "Login", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.padding(LocalPadding.current.micro))
                Text(
                    text = "Please sign in to continue",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(0.5f)
                )
                Spacer(modifier = Modifier.padding(LocalPadding.current.normal))
                MyTextField(
                    value = state.username,
                    isError = state.userNameError != null,
                    supportingText = state.userNameError,
                    onValueChange = {
                        onAction(UserLoginAction.UsernameChanged(it))
                    },
                    label = "Username",
                    placeholder = "Enter your username"
                )
                Spacer(modifier = Modifier.padding(LocalPadding.current.tiny))
                MyTextField(
                    value = state.password,
                    isError = state.passwordError != null,
                    supportingText = state.passwordError,
                    onValueChange = {
                        onAction(UserLoginAction.PasswordChanged(it))
                    },
                    label = "Password",
                    placeholder = "Enter your password",
                    isPassword = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.padding(LocalPadding.current.normal))
                MyButton(isLoading = state.isLoading, buttonText = "Login", onClick = {
                    onAction(UserLoginAction.Login)
                })
            }
        }
    }
}



