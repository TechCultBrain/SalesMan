package com.techcult.salesman.feature.Home.presentation

import com.techcult.salesman.core.data.AdminNavItem

sealed interface HomeAction {

    data class OnMenuItemSelected(val selectedOption: AdminNavItem) : HomeAction
}