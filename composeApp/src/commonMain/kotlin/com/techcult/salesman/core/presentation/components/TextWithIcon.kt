package com.techcult.salesman.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun TextWithIcon(text: String, icon: ImageVector,onClick: () -> Unit={},modifier: Modifier = Modifier)
{
    Row(modifier = Modifier.padding(4.dp).clip(MaterialTheme.shapes.medium).clickable { onClick() },verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
        androidx.compose.material3.Icon(imageVector = icon, contentDescription = null,tint = MaterialTheme.colorScheme.primary)
        Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
        androidx.compose.material3.Text(text = text,style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
    }
}