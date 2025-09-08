package com.techcult.salesman.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.techcult.salesman.core.presentation.theme.LocalPadding

@Composable
fun MyListItem(title: String, subtitle: String)
{

        Column(
            modifier = Modifier.padding(horizontal = LocalPadding.current.normal, vertical = LocalPadding.current.tiny).fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface

            )
            Spacer(modifier = Modifier.height(LocalPadding.current.micro))

            Text(
                subtitle,
                color = MaterialTheme.colorScheme.onSurface.copy(0.5f),
                style = MaterialTheme.typography.bodySmall
            )
        }

}