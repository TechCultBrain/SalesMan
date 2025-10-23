package com.techcult.salesman.core.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CheckboxWithText(
    value: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
    text: String = "Available",
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
) {
    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically, modifier = modifier) {
        Checkbox(checked = value, onCheckedChange = onCheckedChange)
        Text(text = text)

    }

}