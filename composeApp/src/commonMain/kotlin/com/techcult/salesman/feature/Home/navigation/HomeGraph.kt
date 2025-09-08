package com.techcult.salesman.feature.Home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.techcult.salesman.app.Navigation.Screen
import com.techcult.salesman.feature.DashBoard.DashBoardScreen
import com.techcult.salesman.feature.RolePermission.presentation.RolePermissionScreen
import com.techcult.salesman.feature.Sales.presentation.SalesScreen
import com.techcult.salesman.feature.Settings.common.data.presentation.SettingsScreen
import com.techcult.salesman.feature.user.presentation.UserManagementScreen
import com.techcult.salesman.feature.customer.data.CustomerScreen
import com.techcult.salesman.feature.report.presentation.ReportScreen


@Composable
fun HomeGraph(navController: NavHostController,modifier: Modifier) {
    NavHost(navController = navController, startDestination = Screen.DashboardRoute, modifier = modifier) {

        composable<Screen.DashboardRoute> {
            UserManagementScreen()
        }

        composable<Screen.SaleRoute> {
            SalesScreen()

        }
        composable<Screen.ReportRoute> {
            ReportScreen()


        }
        composable<Screen.CustomerRoute> {
            CustomerScreen()

        }
        composable<Screen.SettingsRoute> {
            SettingsScreen(navigateTo = { route ->
                navController.navigate(route)
            }
            )
        }
        composable<Screen.UserManagement>
        {
            UserManagementScreen()
        }
        composable<Screen.StoreInformation>
        {
           // UserManagementScreen()
        }
        composable<Screen.LanguageSettings>
        {
           // UserManagementScreen()
        }
        composable<Screen.NotificationSettings>
        {
            //UserManagementScreen()
        }
        composable<Screen.TaxSettings>
        {
            //UserManagementScreen()
        }
        composable<Screen.ReceiptSettings>
        {
            //UserManagementScreen()
        }
        composable<Screen.PrinterSettings>
        {
            //UserManagementScreen()
        }
        composable<Screen.ProductManagement>
        {
           // UserManagementScreen()
        }
        composable<Screen.SupplierManagement>
        {
            //UserManagementScreen()
        }
        composable<Screen.CategoryScreen>
        {
           // UserManagementScreen()
        }
        composable<Screen.StockManagement>
        {
           // UserManagementScreen()
        }
        composable<Screen.StaffManagement>
        {
           // UserManagementScreen()
        }
        composable<Screen.DashboardSettings>
        {
          //  UserManagementScreen()
        }
        composable<Screen.StoreInformation>
        {
            //UserManagementScreen()
        }
        composable<Screen.LanguageSettings>
        {
            //UserManagementScreen()
        }
        composable<Screen.NotificationSettings>
        {
            //UserManagementScreen()
        }
        composable<Screen.RoleManagement> {
            RolePermissionScreen()
        }

    }
}
