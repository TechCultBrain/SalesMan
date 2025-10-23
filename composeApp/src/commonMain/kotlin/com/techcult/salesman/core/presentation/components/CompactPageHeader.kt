package com.techcult.salesman.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.techcult.salesman.core.presentation.theme.LocalDimensions
import com.techcult.salesman.core.presentation.theme.LocalPadding

@Composable
fun CompactPageHeader(
    icon: ImageVector? = null,
    title: String = "Settings",
    modifier: Modifier = Modifier,
    buttonIcon: ImageVector = Icons.Outlined.Add,
    buttonText: String = "Add",
    onButtonClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {},
    isAddButton: Boolean = false,
    isBackButton: Boolean = false,
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .height(LocalDimensions.current.viewSmall),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {

            icon?.let { Icon(imageVector = it, contentDescription = null) }
            Spacer(modifier = Modifier.width(LocalPadding.current.micro))
            Text(title, style = MaterialTheme.typography.titleMedium)
        }
        if (isAddButton) {

            Surface(shape = MaterialTheme.shapes.medium, color = MaterialTheme.colorScheme.surfaceVariant, onClick = {
                onButtonClicked()
            }) {

                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                    modifier = Modifier.padding(LocalPadding.current.tiny)) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(LocalPadding.current.micro))
                    Text(
                        buttonText,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium
                    )

                }
            }
        }
    }
}