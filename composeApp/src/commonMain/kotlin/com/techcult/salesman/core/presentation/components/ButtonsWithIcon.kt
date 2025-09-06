package com.techcult.salesman.core.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ButtonWithIcon(icon: ImageVector, text: String, onClick: () -> Unit,isOutlined:Boolean=false,modifier: Modifier= Modifier) {
    Button(onClick = onClick, modifier = modifier, shape = MaterialTheme.shapes.small,colors = ButtonDefaults.buttonColors(
        containerColor = if(isOutlined) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.primary,
        contentColor = if(isOutlined) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
    ), border =if(isOutlined) ButtonDefaults.outlinedButtonBorder() else null) {
        Icon(imageVector = icon, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)

    }


}