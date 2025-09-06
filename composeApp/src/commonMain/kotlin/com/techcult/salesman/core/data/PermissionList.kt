package com.techcult.salesman.core.data

import com.techcult.salesman.core.data.database.PermissionEntity

val permissions = listOf(
    PermissionEntity("dashboard.view", "View Dashboard", "Access to main Dashboard", "Dashboard"),
    PermissionEntity(
        "dashboard.analytics",
        "View Analytics",
        "Access to detailed Analytics",
        "Dashboard"
    ),
    PermissionEntity("sales.create", "Create Sales", "Process new sales", "Sales"),
    PermissionEntity("sales.refund", "Process Refund", "Handle refunds and return", "Sales"),
    PermissionEntity("sales.discount", "Apply Discount", "Apply discounts to sales", "Sales"),
    PermissionEntity(
        "sales.view_Reports",
        "View Sales Reports",
        "Access to sales reports",
        "Sales"
    ),
    PermissionEntity("inventory.view", "View Inventory", "View product inventory", "Inventory"),
    PermissionEntity("inventory.create", "Create Products", "Add new inventory items", "Inventory"),
    PermissionEntity(
        "inventory.edit",
        "Edit Inventory",
        "Edit existing inventory items",
        "Inventory"
    ),
    PermissionEntity(
        "inventory.delete",
        "Delete products",
        "Remove products from inventory",
        "Inventory"
    ),
    PermissionEntity(
        "inventory.view_Reports",
        "View Inventory Reports",
        "Access to inventory reports",
        "Inventory"
    ),
    PermissionEntity("users.view", "View Users", "View user accounts", "Users"),
    PermissionEntity("users.create", "Create Users", "Create new user accounts", "Users"),
    PermissionEntity("users.edit", "Edit Users", "Edit user accounts", "Users"),
    PermissionEntity("users.delete", "Delete Users", "Delete user accounts", "Users"),
    PermissionEntity("roles.manage", "Manage Roles", "Create and edit user Roles", "Users"),
    PermissionEntity("settings.general", "General Settings", "General settings", "Settings"),
    PermissionEntity("settings.security", "Security Settings", "Security settings", "Settings"),
    PermissionEntity(
        "settings.notifications",
        "Notification Settings",
        "Notification settings",
        "Settings"
    ),

    )
