package com.techcult.salesman.core.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.techcult.salesman.core.presentation.theme.LocalPadding

@Composable
fun SubHeaderWithIcon(headerText: String, icon: @Composable () -> Unit)
{
    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
        icon()
        Spacer(modifier = Modifier.width(LocalPadding.current.tiny))
        Text(text = headerText, style = MaterialTheme.typography.titleSmall,color = MaterialTheme.colorScheme.onSurfaceVariant)

    }
}