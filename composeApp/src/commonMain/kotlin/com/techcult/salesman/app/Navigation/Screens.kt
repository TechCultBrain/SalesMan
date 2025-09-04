package com.techcult.salesman.app.Navigation

import kotlinx.serialization.Serializable


sealed interface Screen {

    @Serializable
    data object SplashRoute : Screen

    @Serializable
    data object LoginRoute : Screen

    @Serializable
    data object RegisterRoute : Screen

    @Serializable
    data object ForgotPasswordRoute : Screen

    @Serializable
    data object ResetPasswordRoute : Screen

    @Serializable
    data object VerifyOtpRoute : Screen

    @Serializable
    data object HomeRoute : Screen

    @Serializable
    data object ProfileRoute : Screen

    @Serializable
    data object NotificationsRoute : Screen

    @Serializable
    data object SettingsRoute : Screen

    @Serializable
    data object DashboardRoute : Screen

    @Serializable
    data object ProductRoute : Screen

    @Serializable
    data object OrderRoute : Screen

    @Serializable
    data object CustomerRoute : Screen

    @Serializable
    data object InventoryRoute : Screen

    @Serializable
    data object ReportRoute : Screen

    @Serializable
    data object SaleRoute : Screen

    @Serializable
    data object StoreInformation : Screen

    @Serializable
    data object DashboardSettings : Screen

    @Serializable
    data object LanguageSettings : Screen

    @Serializable
    data object NotificationSettings : Screen

    @Serializable
    data object TaxSettings : Screen

    @Serializable
    data object ReceiptSettings : Screen

    @Serializable
    data object PrinterSettings : Screen

    @Serializable
    data object ProductManagement : Screen

    @Serializable
    data object SupplierManagement : Screen

    @Serializable
    data object CategoryScreen : Screen

    @Serializable
    data object StockManagement : Screen

    @Serializable
    data object UserManagement : Screen

    @Serializable
    data object StaffManagement : Screen



}