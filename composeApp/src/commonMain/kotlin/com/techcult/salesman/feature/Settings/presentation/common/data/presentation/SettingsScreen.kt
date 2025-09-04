package com.techcult.salesman.feature.Settings.presentation.common.data.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Print
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.techcult.salesman.core.presentation.components.HeaderWithIcon
import com.techcult.salesman.core.presentation.components.TextListItem
import com.techcult.salesman.core.presentation.theme.LocalPadding
import com.techcult.salesman.core.utils.DeviceConfiguration
import com.techcult.salesman.feature.Settings.presentation.common.data.SettingsRouting

@Composable
fun SettingsScreen(navigateTo: (Any) -> Unit) {

    SettingsScreenContent(modifier = Modifier, onAction = {action->
       when(action){
           is SettingsAction.OnSettingOptionClicked->{
               navigateTo(action.option.route)
           }

       }
    })

}


@Composable
fun SettingsScreenContent(modifier: Modifier, onAction: (SettingsAction) -> Unit) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
    val scrollState= rememberScrollState(initial = 2)
    Scaffold {
        when (deviceConfiguration) {
            DeviceConfiguration.TABLET_LANDSCAPE -> {
                SettingsWideScreen(onAction)
            }

            DeviceConfiguration.DESKTOP -> {
                SettingsWideScreen(onAction)
            }

            else -> {
                SettingsCompactScreen(onAction)
            }
        }


    }
}


@Composable
fun SettingsWideScreen(onAction: (SettingsAction) -> Unit)
{
    Surface(modifier = Modifier.fillMaxSize().padding(all=16.dp),) {
        Column(
            Modifier.fillMaxSize()

        )
        {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Icon(imageVector = Icons.Outlined.Settings, contentDescription = null)
                Spacer(modifier = Modifier.width(LocalPadding.current.tiny))
                Text("Settings", style = MaterialTheme.typography.titleLarge)
            }
            Spacer(modifier = Modifier.height(LocalPadding.current.micro))
            Text(
                "Configure your pos and Manage Store Settings",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.large))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(LocalPadding.current.tiny)
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight().weight(1f),
                    verticalArrangement = Arrangement.spacedBy(LocalPadding.current.tiny)
                ) {


                    GeneralSettings(modifier = Modifier.weight(1f), onAction = onAction)


                    ReceiptSettings(modifier = Modifier.weight(1f), onAction = onAction)


                }
                Spacer(modifier = Modifier.height(LocalPadding.current.large))
                Column(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    verticalArrangement = Arrangement.spacedBy(LocalPadding.current.tiny)
                ) {

                    StaffSettings(modifier = Modifier.weight(1f), onAction = onAction)


                    InventorySettings(modifier = Modifier.weight(1f), onAction)
                }
            }
        }
    }
}

@Composable
fun SettingsCompactScreen(onAction: (SettingsAction) -> Unit)
{
    Surface(modifier = Modifier.fillMaxSize().padding(all=16.dp),) {
        Column(
            Modifier.fillMaxSize().verticalScroll(rememberScrollState())

        )
        {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Icon(imageVector = Icons.Outlined.Settings, contentDescription = null)
                Spacer(modifier = Modifier.width(LocalPadding.current.tiny))
                Text("Settings", style = MaterialTheme.typography.titleLarge)
            }
            Spacer(modifier = Modifier.height(LocalPadding.current.micro))
            Text(
                "Configure your pos and Manage Store Settings",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.large))






            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(LocalPadding.current.tiny)
            ) {
                GeneralSettings(modifier = Modifier, onAction = onAction)
                ReceiptSettings(modifier = Modifier, onAction = onAction)
                StaffSettings(modifier = Modifier, onAction = onAction)
                InventorySettings(modifier = Modifier, onAction = onAction)

            }
        }
    }

}


@Composable
fun StaffSettings(modifier: Modifier, onAction: (SettingsAction) -> Unit) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            1.dp,
            color = MaterialTheme.colorScheme.surfaceContainer.copy(0.7f)
        )
    ) {

        Column(modifier = Modifier.padding(LocalPadding.current.large)) {
            HeaderWithIcon(
                icon = Icons.Outlined.Person,
                surfaceColor = Color(0xFFffe6a5),
                iconColor = Color(0xFFe88b5c),
                titleText = "User & Staff Management",
                subtitleText = "Manage users and staff members"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.extraLarge))
            TextListItem(
                onClick = {
                    onAction(SettingsAction.OnSettingOptionClicked(SettingsRouting.UserManagement))
                },
                titleText = "User Management",
                subtitleText = "Configure tax rates and calculations"
            )
            TextListItem(
                onClick = {
                    onAction(SettingsAction.OnSettingOptionClicked(SettingsRouting.StaffManagement))
                },
                titleText = "Staff Management",
                subtitleText = "Configure tax rates and calculations"
            )


        }
    }


}

@Composable
fun ReceiptSettings(modifier: Modifier, onAction: (SettingsAction) -> Unit) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            1.dp,
            color = MaterialTheme.colorScheme.surfaceContainer.copy(0.7f)
        )
    ) {
        Column(modifier = Modifier.padding(LocalPadding.current.large)) {
            HeaderWithIcon(
                icon = Icons.Outlined.Print,
                surfaceColor = Color(0xFF86d3ff),
                iconColor = Color(0xFF868fff),
                titleText = "Tax & Printing",
                subtitleText = "Receipt template and printer configuration"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.extraLarge))

            TextListItem(
                onClick = {
                    onAction(SettingsAction.OnSettingOptionClicked(SettingsRouting.TaxSettings))

                },
                titleText = "Tax Settings",
                subtitleText = "Configure tax rates and calculations"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.extraLarge))
            TextListItem(
                onClick = {},
                titleText = "Receipt Template",
                subtitleText = "Customize receipt layout and branding"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.large))
            TextListItem(
                onClick = {},
                titleText = "Dashboard Settings",
                subtitleText = "Customize your dashboard layout and widgets"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.large))
            TextListItem(
                onClick = {},
                titleText = "Printer & Device Settings",
                subtitleText = "Configure printer and hardware devices"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.large))


        }
    }

}

@Composable
fun GeneralSettings(modifier: Modifier,onAction: (SettingsAction) -> Unit) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            1.dp,
            color = MaterialTheme.colorScheme.surfaceContainer.copy(0.7f)
        )
    ) {
        Column(modifier = Modifier.padding(LocalPadding.current.large)) {
            HeaderWithIcon(
                icon = Icons.Outlined.Store,
                surfaceColor = Color(0xFFcceff9),
                iconColor = Color(0xFF00b5e2),
                titleText = "General Settings",
                subtitleText = "Basic Store and System Configuration"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.extraLarge))
            TextListItem(
                onClick = {},
                titleText = "Store Information",
                subtitleText = "Business details and contact information"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.large))
            TextListItem(
                onClick = {
                    onAction(SettingsAction.OnSettingOptionClicked(SettingsRouting.DashboardSettings))

                },
                titleText = "Dashboard Settings",
                subtitleText = "Customize your dashboard layout and widgets"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.large))
            TextListItem(
                onClick = {
                    onAction(SettingsAction.OnSettingOptionClicked(SettingsRouting.LanguageSettings))
                },
                titleText = "Language & Currency",
                subtitleText = "Localizations and currency settings"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.large))
            TextListItem(
                onClick = {
                    onAction(SettingsAction.OnSettingOptionClicked(SettingsRouting.NotificationSettings))
                },
                titleText = "Notifications",
                subtitleText = "Receive notifications and alerts"
            )

        }
    }

}

@Composable
fun InventorySettings(modifier: Modifier,onAction: (SettingsAction) -> Unit) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            1.dp,
            color = MaterialTheme.colorScheme.surfaceContainer.copy(0.7f)
        )
    ) {
        Column(modifier = Modifier.padding(LocalPadding.current.large)) {
            HeaderWithIcon(
                icon = Icons.Default.AccountBox,
                surfaceColor = Color(0xFFecc9c9),
                iconColor = Color(0xFFff9b9b),
                titleText = "Inventory Management",
                subtitleText = "Product and stock management Settings"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.extraLarge))
            TextListItem(
                onClick = {
                    onAction(SettingsAction.OnSettingOptionClicked(SettingsRouting.ProductManagement))
                },
                titleText = "Product Management",
                subtitleText = "Manage product inventory and prices"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.large))
            TextListItem(
                onClick = {
                    onAction(SettingsAction.OnSettingOptionClicked(SettingsRouting.SupplierManagement))
                },
                titleText = "Suppliers",
                subtitleText = "Manage supplier information and relationship"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.large))
            TextListItem(
                onClick = {
                    onAction(SettingsAction.OnSettingOptionClicked(SettingsRouting.CategoryScreen))
                },
                titleText = "Categories",
                subtitleText = "Organize products into categories and subcategories"
            )
            Spacer(modifier = Modifier.height(LocalPadding.current.large))
            TextListItem(
                onClick = {
                    onAction(SettingsAction.OnSettingOptionClicked(SettingsRouting.StockManagement))
                },
                titleText = "StockLevel",
                subtitleText = "Monitor and Manage Inventory levels"
            )


        }
    }

}