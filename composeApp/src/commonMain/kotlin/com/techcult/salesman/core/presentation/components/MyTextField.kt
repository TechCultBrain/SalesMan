package com.techcult.salesman.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techcult.salesman.core.presentation.theme.LocalPadding
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
    val interactionSource = remember { MutableInteractionSource() }
    val focused = interactionSource.collectIsFocusedAsState()

    Column(modifier = modifier.padding()) {
        if (label != null) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        BasicTextField(
            readOnly = readOnly,
            modifier = Modifier.padding(

            ), // Padding for the BasicTextField content

            value = value,
            onValueChange = { it ->
                onValueChange(it)
            },
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            maxLines = maxLines,

            decorationBox = { innerTextField ->
                Surface(
                    modifier = Modifier.height(48.dp).fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (focused.value) {
                            MaterialTheme.colorScheme.onSurfaceVariant.copy(0.4f)
                        } else {
                            Color.Transparent
                        }
                    ),
                    color = MaterialTheme.colorScheme.surfaceContainer
                ) {
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .fillMaxSize()

                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (leadingIcon != null) {
                                androidx.compose.material3.Icon(
                                    imageVector = leadingIcon,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Surface(
                                modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                                color = Color.Transparent
                            ) {
                                Box(
                                    modifier = Modifier.padding(vertical = 4.dp).fillMaxSize(),
                                    contentAlignment = Alignment.CenterStart
                                ) {

                                    innerTextField()

                                    if (value.isEmpty() && placeholder != null) {

                                        Text(
                                            text = placeholder,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                                                0.5f
                                            )
                                        )


                                    }

                                }

                            }
                            // innerTextField()
                            if (trailingIcon != null) {
                                IconButton(onClick = onTrailingIconClick) {
                                    androidx.compose.material3.Icon(
                                        imageVector = trailingIcon,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    )
                                }
                            }

                        }
                    }
                }
            },
            interactionSource = interactionSource

        )
        Spacer(modifier = Modifier.height(4.dp))
        if (isError) {
            Text(
                text = supportingText.toString(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.error
            )
        }
        else if (supportingText != null) {
            Text(
                text = supportingText,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.5f)
            )
        }

    }

}

@Preview()
@Composable
fun MyTextPreview() {
    Surface {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            MyTextField(value = "Hello World", onValueChange = {})
        }
    }
}