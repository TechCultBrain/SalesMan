package com.techcult.salesman.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun HeaderWithIcon(
    icon: ImageVector,
    surfaceColor: Color = Color(0xFFFFF),
    iconColor: Color = Color.Black,
    titleText: String,
    subtitleText: String
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start)
    {
        Surface(
            color = surfaceColor,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.size(32.dp)
        ) {

            Icon(imageVector = icon, contentDescription = null, tint = iconColor,modifier = Modifier.padding(4.dp))

        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = titleText, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitleText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )


        }

    }
}