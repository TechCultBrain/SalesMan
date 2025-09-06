package com.techcult.salesman.feature.Settings.common.data

import com.techcult.salesman.app.Navigation.Screen

enum class SettingsRouting(val route: Any) {

   StoreInformation(Screen.StoreInformation),
   DashboardSettings(Screen.DashboardSettings),
   LanguageSettings(Screen.LanguageSettings),
   NotificationSettings(Screen.NotificationSettings),
   TaxSettings(Screen.TaxSettings),
   ReceiptSettings(Screen.ReceiptSettings),
   PrinterSettings(Screen.PrinterSettings),
   ProductManagement(Screen.ProductManagement),
   SupplierManagement(Screen.SupplierManagement),
   CategoryScreen(Screen.CategoryScreen),
   StockManagement(Screen.StockManagement),
   UserManagement(Screen.UserManagement),
   StaffManagement(Screen.StaffManagement),

   RoleManagement(Screen.RoleManagement),


}