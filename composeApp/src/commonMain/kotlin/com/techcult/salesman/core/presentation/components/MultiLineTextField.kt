package com.techcult.salesman.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MultilineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    placeholder: String? = null,
    label: String? = null
) {
    Column {
        if (label != null) {
            Text(text =label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant)

            Spacer(modifier = Modifier.height(4.dp))
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            maxLines = maxLines,
            singleLine = false,
            decorationBox = { innerTextField ->
                Box(
                    modifier = modifier.fillMaxWidth().height(100.dp)
                        .clip(shape = MaterialTheme.shapes.medium).background(
                            color = MaterialTheme.colorScheme.surfaceContainerHighest
                    ).padding(16.dp)
                ) {
                    innerTextField()
                    if (value.isEmpty() && placeholder != null) {

                        Text(
                            text = placeholder,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )


                    }
                }

            }

        )
    }

}