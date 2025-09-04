package com.techcult.salesman.feature.auth.presentation.forgotpassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.KeyboardDoubleArrowLeft
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
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
import com.techcult.salesman.feature.auth.presentation.login.WelcomeImage
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import salesman.composeapp.generated.resources.Res
import salesman.composeapp.generated.resources.compose_multiplatform

@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onSuccess: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    ForgotPasswordContent(
        state = state,
        onBackClick = onBackClick,
        action = viewModel::onAction
    )


}

@Composable
fun ForgotPasswordContent(
    state: ForgotPasswordState,
    onBackClick: () -> Unit,
    action: (ForgotActions) -> Unit,
    onSupportClick: () -> Unit = {}
) {

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)

    when (deviceConfiguration) {

        DeviceConfiguration.DESKTOP -> {

            ForgotPasswordScreenUi(
                isWide = true,
                state = state,
                onBackClick = onBackClick,
                onAction = action,
                onSupportClick = onSupportClick
            )

        }

        else -> {
            ForgotPasswordScreenUi(
                isWide = false,
                state = state,
                onBackClick = onBackClick,
                onAction = action,
                onSupportClick = onSupportClick
            )

        }
    }

}


@Composable
fun ForgotPasswordScreenUi(
    isWide: Boolean = false,
    state: ForgotPasswordState,
    onBackClick: () -> Unit,
    onAction: (ForgotActions) -> Unit,
    onSupportClick: () -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.onPrimary) {
        Row(
            modifier = Modifier.fillMaxSize().padding(
                horizontal = LocalPadding.current.none,
                vertical = LocalPadding.current.none
            ),
        ) {

            if (isWide) {
                WelcomeImage(modifier = Modifier.weight(1.5f).fillMaxSize())
            }

            Surface(
                modifier = Modifier.weight(1f)
                    .padding(if (isWide) LocalPadding.current.large else LocalPadding.current.none)
                    .fillMaxSize(),
                color =  MaterialTheme.colorScheme.onPrimary
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Surface(
                        shape = RoundedCornerShape(0.dp),
                        modifier = Modifier.padding(0.dp),
                        shadowElevation =  0.dp,
                        color = MaterialTheme.colorScheme.onPrimary,
                    ) {

                        ForgotPasswordForm(state, onAction, onBackClick, onSupportClick)
                        if (state.isLoading) {
                            LoadingIndicator()
                        }

                    }
                }
            }
        }
    }

}


@Composable
fun ForgotPasswordForm(
    state: ForgotPasswordState,
    onAction: (ForgotActions) -> Unit,
    onBackClick: () -> Unit,
    onSupportClick: () -> Unit
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
        Text(text = "Forgot Password", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.size(LocalPadding.current.tiny))
        Text(
            text = "Enter your mobile no to receive verification to reset your password.",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.large))
        MyTextField(
            modifier = Modifier,
            label = "Mobile No",
            value = state.emailId,
            onValueChange = {
                onAction(ForgotActions.OnEmailChange(it))
            },
            leadingIcon = Icons.Outlined.Phone,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.large))
        MyButton(
            modifier = Modifier,
            buttonText = "Send Code",
            onClick = {
                onAction(ForgotActions.OnSendOtp)
            },
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.tiny))
        Row(modifier = Modifier.clickable {
            onBackClick()
        }, verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Outlined.KeyboardDoubleArrowLeft, contentDescription = null)

            Text(
                "Go To Login Page",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
            )

        }
        Spacer(modifier = Modifier.size(LocalPadding.current.large))
        TextWithClick(firstText = "Need Help?", secondText = "Contact Us") {
            onSupportClick()
        }


    }
}


