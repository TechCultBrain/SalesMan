package com.techcult.salesman.core.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MySearchBar(
    modifier: Modifier,
    labelText: String,
    value: String,
    onValueChange: (String) -> Unit
) {


    Surface(
        modifier = modifier.height(54.dp).fillMaxWidth(0.5f),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceContainer
    ) {

        Row(
            modifier = Modifier.padding(horizontal =
                16.dp
            ).fillMaxSize(), verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = value,
                onValueChange = {onValueChange(it)},
                placeholder = { Text(text = labelText) },
                textStyle = MaterialTheme.typography.bodyMedium,
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
            )

        }


    }

}