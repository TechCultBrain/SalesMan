package com.techcult.salesman.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RoundedBorderTextSurface(text: String) {
    Surface(
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        color = Color.Transparent
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp,vertical = 4.dp),
            color = Color.Black,
            style = MaterialTheme.typography.bodySmall
        )
    }
}