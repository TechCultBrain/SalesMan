package com.techcult.salesman.feature.auth.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Composable
fun OtpInputField(
    modifier: Modifier = Modifier,
    otpText: String = "",
    otpLength: Int = 4,
    shouldShowCursor: Boolean,
    shouldCursorBlink: Boolean,
    onOtpModified: (String, Boolean) -> Unit = { a, b -> }
) {
    BasicTextField(modifier = modifier,
        value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
        onValueChange = {
            if (it.text.length <= otpLength) {
                onOtpModified(it.text, it.text.length == otpLength)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done
        ),
        decorationBox = {
            Row(
                modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                repeat(otpLength) { index ->
                    CharacterContainer(
                        index, otpText, shouldShowCursor, shouldCursorBlink
                    )
                }
            }
        })
}

@Composable
fun CharacterContainer(
    index: Int,
    otpText: String,
    shouldShowCursor: Boolean,
    shouldCursorBlink: Boolean,
    transformationChar: String = "*"
) {

    val isFocused = otpText.length == index

    val character = when {
        index < otpText.length -> {
            transformationChar
        }

        else -> ""
    }

    val cursorVisible = remember {
        mutableStateOf(shouldShowCursor)
    }

    LaunchedEffect(
        key1 = isFocused
    ) {
        if (isFocused && shouldShowCursor && shouldCursorBlink) {
            while (true) {
                delay(500) //Adjust blinking speed here
                cursorVisible.value = !cursorVisible.value
            }
        }
    }

    Box(
        modifier = Modifier.border(1.dp, MaterialTheme.colorScheme.primaryContainer, MaterialTheme.shapes.small),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.width(55.dp),
            text = character,
            style = MaterialTheme.typography.displayMedium,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        AnimatedVisibility(
            visible = isFocused && cursorVisible.value
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(2.dp)
                    .height(25.dp)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }
    }
}