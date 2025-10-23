package com.techcult.salesman.core.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StatusLabel(isActive: Boolean) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.wrapContentWidth()
    ) {
        Text(
            text = if (isActive) "Active" else "Inactive",
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.surface
        )
    }

}