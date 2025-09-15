package com.techcult.salesman.feature.Home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcult.salesman.core.data.permissions
import com.techcult.salesman.feature.Settings.RolePermission.domain.RolePermissionRepository
import com.techcult.salesman.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(val rolePermissionRepository: RolePermissionRepository,val authRepository: AuthRepository) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch() {

            if (rolePermissionRepository.countPermissions() == 0) {
                rolePermissionRepository.insertPermissions(permissions)

            }
        }

    }


    fun onAction(action: HomeAction) {

        when (action) {
            is HomeAction.OnMenuItemSelected -> {
                _state.update {
                    it.copy(selectedOption = action.selectedOption)
                }
            }


        }
    }

    fun logout()
    {
        viewModelScope.launch {
            authRepository.logoutUser()
        }

    }


}