package com.techcult.salesman.feature.user.presentation


data class UserUiState(val isLoading: Boolean = false, val users: List<UserUi> = emptyList())
