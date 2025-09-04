package com.techcult.salesman.feature.auth.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.techcult.salesman.core.presentation.theme.LocalPadding

@Composable
fun BackToLogin(onClick: () -> Unit) {

    Row(
        modifier = Modifier.clickable {
            onClick()
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            LocalPadding.current.tiny
        )
    ) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null
        , tint = MaterialTheme.colorScheme.onSurface.copy(0.5f), modifier = Modifier.size(24.dp))
        Text(
            "Back To Login",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(0.5f)
        )

    }
}