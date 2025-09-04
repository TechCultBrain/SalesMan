package com.techcult.salesman.feature.Home.presentation

import com.techcult.salesman.core.data.AdminNavItem

data class HomeUiState(
    val isLoading: Boolean = true,
    val selectedOption: AdminNavItem = AdminNavItem.HOME
)
