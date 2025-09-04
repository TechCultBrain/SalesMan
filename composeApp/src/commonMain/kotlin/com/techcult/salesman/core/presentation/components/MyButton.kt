package com.techcult.salesman.core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyButton(
    isLoading: Boolean = false,
    modifier: Modifier= Modifier,
    buttonText: String = "Click Me",
    onClick: () -> Unit,
    isEnabled: Boolean = true,
    buttonColors: ButtonColors = androidx.compose.material3.ButtonDefaults.buttonColors()
) {

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        colors = buttonColors,
        shape = MaterialTheme.shapes.medium
    ) {

        AnimatedVisibility(isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = MaterialTheme.colorScheme.onPrimary,

            )

        }
        AnimatedVisibility(!isLoading) {
            Text(buttonText)
        }
    }


}