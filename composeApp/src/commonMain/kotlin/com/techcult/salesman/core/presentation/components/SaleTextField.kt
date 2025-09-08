package com.techcult.salesman.core.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.techcult.salesman.core.presentation.theme.LocalDimensions
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: ImageVector? = null,
    prefix: String? = null,
    suffix: String? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: () -> Unit = {},
    isError: Boolean = false,
    supportingText: String? = null,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Next,
        keyboardType = androidx.compose.ui.text.input.KeyboardType.Password
    ),
    readOnly: Boolean = false

) {
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        readOnly =readOnly ,
        modifier = modifier.widthIn(
            min = LocalDimensions.current.minWidthForTextField,
            max = LocalDimensions.current.maxWidthForTextField
        ),
        value = value,
        onValueChange = onValueChange,
        label = if (label != null) {
            { Text(text = label) }
        } else {
            null
        },
        placeholder = if (placeholder != null) {
            { Text(text = placeholder) }
        } else {
            null
        },
        leadingIcon = if (leadingIcon != null) {
            { Icon(imageVector = leadingIcon, contentDescription = null) }
        } else {
            null
        },
        trailingIcon = if (trailingIcon != null || isPassword) {
            {
                if (isPassword) {
                    IconButton(onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }) {

                        if (isPasswordVisible) {
                            Icon(
                                imageVector = Icons.Outlined.Visibility,
                                contentDescription = null
                            )
                        } else {
                            Icon(imageVector = Icons.Outlined.VisibilityOff, contentDescription = null)
                        }
                    }
                } else {
                    trailingIcon?.let {
                        IconButton(onClick = {
                            onTrailingIconClick()
                        }) {
                            Icon(imageVector = it, contentDescription = null)
                        }
                    }
                }
            }
        } else {
            null
        },
        prefix = if (prefix != null) {
            { Text(text = prefix) }
        } else {
            null
        },
        suffix = if (suffix != null) {
            { Text(text = suffix) }
        } else {
            null
        },
        isError = isError,
        supportingText = if (supportingText != null) {
            { Text(text = supportingText) }
        } else {
            null
        },
        singleLine = singleLine,
        maxLines = maxLines,
        visualTransformation = if (isPassword) {
            if (isPasswordVisible) {
                androidx.compose.ui.text.input.VisualTransformation.None
            }else{

                androidx.compose.ui.text.input.PasswordVisualTransformation()
            }
        } else {
            androidx.compose.ui.text.input.VisualTransformation.None

        },
        keyboardOptions = if (isPassword) {
            KeyboardOptions(
                keyboardType = androidx.compose.ui.text.input.KeyboardType.Password,
                imeAction = ImeAction.Next
            )
        } else {
            keyboardOptions
        },
        colors = TextFieldDefaults.colors()
            .copy(focusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                errorContainerColor = MaterialTheme.colorScheme.onPrimary
            ),
        shape = MaterialTheme.shapes.medium,



    )

}


@Composable
@Preview()
fun SaleTextFieldPreview() {

    MyTextField(value = "Hello World", onValueChange = {}, label = "welcome")
}
