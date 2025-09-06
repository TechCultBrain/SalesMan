package com.techcult.salesman.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.techcult.salesman.core.presentation.theme.LocalDimensions
import com.techcult.salesman.core.presentation.theme.LocalPadding
import com.techcult.salesman.core.utils.DeviceConfiguration

@Composable
fun HeaderTextWithIcon(
    icon: ImageVector? = null,
    title: String="Settings",
    subtitle: String? = null,
    modifier: Modifier = Modifier,
    deviceConfiguration: DeviceConfiguration=DeviceConfiguration.TABLET_LANDSCAPE,
    buttonIcon: ImageVector = Icons.Outlined.Add,
    buttonText: String = "Add",
    onButtonClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {}
) {
    if (deviceConfiguration == DeviceConfiguration.MOBILE_PORTRAIT) {
        Row (
            modifier = Modifier.fillMaxWidth()
                .height(LocalDimensions.current.viewSmall),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {

            icon?.let { Icon(imageVector = it, contentDescription = null) }
            Spacer(modifier = Modifier.width(LocalPadding.current.micro))
            Text(title, style = MaterialTheme.typography.titleMedium)
        }


    } else {
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(LocalDimensions.current.viewLarge),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Row(modifier= Modifier.weight(1f)) {


                Column(modifier = modifier) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        if (icon != null) {
                            Icon(imageVector = icon, contentDescription = null)
                        }
                        Spacer(modifier = Modifier.width(LocalPadding.current.tiny))
                        Text(title, style = MaterialTheme.typography.titleLarge)
                    }
                    Spacer(modifier = Modifier.height(LocalPadding.current.micro))
                    if (subtitle != null) {
                        Text(
                            subtitle,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                        )
                    }
                }
            }
            ButtonWithIcon(icon = buttonIcon, text = buttonText, onClick = onButtonClicked)

        }
    }
}

