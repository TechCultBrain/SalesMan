package com.techcult.salesman.core.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.PointOfSale
import androidx.compose.material.icons.outlined.Settings

import androidx.compose.ui.graphics.vector.ImageVector
import com.techcult.salesman.app.Navigation.Screen
import org.jetbrains.compose.resources.DrawableResource
import salesman.composeapp.generated.resources.Res
import salesman.composeapp.generated.resources.ic_home
import salesman.composeapp.generated.resources.ic_home_24
import salesman.composeapp.generated.resources.ic_settings_24


enum class BusinessType(val typeName: String) {
    RETAIL("Retail"),
    RESTAURANT("Restaurant"),
    TEXTILE("Textile"),
    GROCERY("Grocery"),
    OTHER("Other")


}

enum class AdminNavItem(
    val itemName: String,
    val route: Any,
    val icon: ImageVector
) {
    HOME("Home", Screen.DashboardRoute, Icons.Outlined.Home),
    SALES("Sales",Screen.SaleRoute,Icons.Outlined.PointOfSale),
    REPORTS("Reports",Screen.ReportRoute,Icons.Outlined.BarChart),
    SETTINGS("Settings",Screen.SettingsRoute,Icons.Outlined.Settings)

}