package com.techcult.salesman.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.techcult.salesman.core.presentation.theme.LocalPadding
import com.techcult.salesman.core.utils.DeviceConfiguration

@Composable
fun WidePageHeader(
    icon: ImageVector? = null,
    title: String = "Settings",
    subtitle: String? = null,
    modifier: Modifier = Modifier,
    buttonIcon: ImageVector = Icons.Outlined.Add,
    buttonText: String = "Add",
    onButtonClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {},
    onCancelButtonClicked: () -> Unit = {},
    isAddButton: Boolean = false,
    isBackButton: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.weight(1f)) {


            Column(modifier = modifier) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBackClicked)
                    {
                        if (isBackButton) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = null
                            )
                        } else {
                            icon?.let {
                                Icon(imageVector = it, contentDescription = null)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(LocalPadding.current.tiny))
                    Text(title, style = MaterialTheme.typography.titleLarge)
                }
                Spacer(modifier = Modifier.height(LocalPadding.current.micro))
                if (subtitle != null) {
                    Text(
                        subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    )
                }
            }
        }
        if (isAddButton) {
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.normal)
            ) {
                ButtonWithIcon(
                    icon = Icons.Default.Cancel,
                    text = "Cancel",
                    onClick = onCancelButtonClicked,
                    isOutlined = true
                )

                ButtonWithIcon(icon = buttonIcon, text = buttonText, onClick = onButtonClicked,)
            }
        }

    }


}