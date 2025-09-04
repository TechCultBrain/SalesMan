package com.techcult.salesman.feature.auth.presentation.verify

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techcult.salesman.core.presentation.components.LoadingIndicator
import com.techcult.salesman.core.presentation.components.MyButton
import com.techcult.salesman.core.presentation.components.TextWithClick
import com.techcult.salesman.core.presentation.theme.LocalDimensions
import com.techcult.salesman.core.presentation.theme.LocalPadding
import com.techcult.salesman.core.utils.DeviceConfiguration
import com.techcult.salesman.core.utils.timeFormatter
import com.techcult.salesman.feature.auth.presentation.component.OtpInputField
import com.techcult.salesman.feature.auth.presentation.login.WelcomeImage
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import salesman.composeapp.generated.resources.Res
import salesman.composeapp.generated.resources.compose_multiplatform

@Composable
fun VerifyOTPScreen(
    onSupportClick: () -> Unit,
    onBackClick: () -> Unit,
    onSuccess: () -> Unit,
    viewModel: VerifyOtpViewModel = koinViewModel()
) {


    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.isRunning && state.timeLeft > 0) {
        if (state.isRunning && state.timeLeft > 0) {
            while (state.timeLeft > 0) {
                delay(1000)
                viewModel.reduceTimeLeft()
            }

        }


    }
    VerifyOTPContent(state, viewModel::onAction, onSupportClick)

}


@Composable
fun VerifyOTPContent(
    state: VerifyOtpState,
    onAction: (OtpAction) -> Unit,
    onSupportClick: () -> Unit
) {

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)

    when (deviceConfiguration) {

        DeviceConfiguration.DESKTOP -> {

            VerifyOTPScreenUi(state, onAction, true, onSupportClick)
        }

        else -> {
            VerifyOTPScreenUi(state, onAction, false, onSupportClick)


        }
    }


}

@Composable
fun VerifyOTPScreenUi(
    state: VerifyOtpState,
    onAction: (OtpAction) -> Unit,
    isWide: Boolean,
    onSupportClick: () -> Unit
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

                        VerifyOTPForm(state, onAction, onSupportClick)


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
fun VerifyOTPForm(
    state: VerifyOtpState,
    onAction: (OtpAction) -> Unit,
    onSupportClick: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }

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
        Text(text = "Verify OTP", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.size(LocalPadding.current.tiny))
        Text(
            text = "Enter the code sent to your mobile no .",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.large))
        OtpInputField(
            modifier = Modifier.focusRequester(focusRequester),
            shouldShowCursor = true,
            shouldCursorBlink = true,
            onOtpModified = { value, isValid ->
                onAction(OtpAction.OnOtpChange(value))
            },
            otpText = state.otpText
        )

        Spacer(modifier = Modifier.size(LocalPadding.current.large))




        MyButton(
            modifier = Modifier,
            buttonText = "Verify Code",
            onClick = {
                onAction(OtpAction.VerifyOtp)
            },
        )
        Spacer(modifier = Modifier.size(LocalPadding.current.large))
        if (state.timeLeft > 0) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = "Remaining Time",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(LocalPadding.current.small))
                Text(
                    modifier =Modifier.width(60.dp) ,
                    text = timeFormatter(state.timeLeft),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        } else {


            TextWithClick(firstText = "Didn't receive the code?", secondText = "Resend") {
                onAction(OtpAction.ResendOtp)
            }
        }


    }
}

