package com.techcult.salesman.feature.RolePermission.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techcult.salesman.core.data.database.RoleEntity
import com.techcult.salesman.feature.RolePermission.domain.RolePermissionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RoleViewModel(private val rolePermissionRepository: RolePermissionRepository) : ViewModel() {


    private val _state = MutableStateFlow(RolePermissionUiState())
    val state = _state
        .onStart {
            val permissions = rolePermissionRepository.getPermissions()
            _state.value = _state.value.copy(
                permissions = permissions,
                permissionChecked = permissions.map { permission ->
                    PermissionChecked(
                        permission,
                        false
                    )
                }.toMutableList()
            )
            loadRoles()


        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), _state.value)


    init {


    }

    fun onAction(action: RolePermissionAction) {
        when (action) {
            is RolePermissionAction.OnAddRoleClicked -> {
                _state.update {
                    it.copy(isRoleAddDialogVisible = action.isRoleAddDialogVisible)

                }
            }

            is RolePermissionAction.OnPermissionSelected -> {
                val permissions = _state.value.permissionChecked.map {
                    if (it.permissionEntity.permissionId == action.permissionId) {
                        it.copy(isChecked = !action.isChecked)
                    } else {
                        it
                    }
                }
                _state.update {
                    it.copy(permissionChecked = permissions.toMutableList(), permissionError = null)


                }


            }

            is RolePermissionAction.OnRoleDescriptionChanged -> {
                _state.update {
                    it.copy(roleDescription = action.roleDescription, roleDescriptionError = null)
                }

            }

            is RolePermissionAction.OnRoleNameChanged -> {
                _state.update {
                    it.copy(
                        roleName = action.roleName,
                        roleNameError = null,
                        roleDescriptionError = null
                    )

                }
            }

            RolePermissionAction.OnSaveClicked -> {
                if (_state.value.roleName.isEmpty()) {
                    _state.update {
                        it.copy(roleNameError = "Role Name is required")
                    }
                } else if (_state.value.roleDescription.isEmpty()) {
                    _state.update {
                        it.copy(roleDescriptionError = "Role Description is required")
                    }

                } else if (_state.value.permissionChecked.none { it.isChecked }) {
                    _state.update {
                        it.copy(permissionError = "Please select at least one permission")
                    }

                } else {
                    _state.update {
                        it.copy(isRoleAddDialogVisible = false)
                    }
                    if (_state.value.selectedRole == null) {
                        viewModelScope.launch {
                            val permissions = _state.value.permissionChecked.filter { it.isChecked }
                            rolePermissionRepository.createRole(
                                _state.value.roleName,
                                _state.value.roleDescription,
                                permissions.map { permissionChecked -> permissionChecked.permissionEntity.permissionId }
                            )
                            clearState()

                        }
                    } else {
                        viewModelScope.launch {
                            val permissions = _state.value.permissionChecked.filter { it.isChecked }
                            rolePermissionRepository.updateRole(
                                _state.value.selectedRole!!.roleId,
                                _state.value.roleName,
                                _state.value.roleDescription,
                                permissions.map { permissionChecked -> permissionChecked.permissionEntity.permissionId }
                            )
                            clearState()
                        }

                    }
                }
            }

            is RolePermissionAction.OnEditRoleClicked -> {
                _state.update {
                    it.copy(
                        isRoleAddDialogVisible = action.isRoleAddDialogVisible,
                        selectedRole = it.rolePermission.find { role ->
                            role.roleId == action.id
                        })
                }
                val permissions = _state.value.permissionChecked.map {
                    if (_state.value.selectedRole!!.permissionId.contains(it.permissionEntity.permissionId)) {
                        it.copy(isChecked = true)
                    } else {
                        it
                    }
                }


                _state.update {
                    it.copy(
                        roleName = _state.value.selectedRole!!.roleName.toString(),
                        roleDescription = _state.value.selectedRole!!.roleDescription.toString(),
                        permissionChecked = permissions.toMutableList()
                    )
                }
            }

            is RolePermissionAction.OnViewRoleClicked -> {
                _state.update {
                    it.copy(
                        isDeleteOptionSelected = true,
                        isRoleDetailDialogVisible = action.isRoleDetailDialogVisible,
                        selectedRole = it.rolePermission.find { role ->
                            role.roleId == action.id
                        })

                }


            }

            is RolePermissionAction.OnDeleteRoleClicked -> {
                _state.update {
                    it.copy(
                        isDeleteOptionSelected = false,
                        isRoleDetailDialogVisible = false
                    )
                }
                val roleEntity = RoleEntity(
                    roleId = _state.value.selectedRole!!.roleId,
                    roleName = _state.value.selectedRole!!.roleName.toString(),
                    roleDescription = _state.value.selectedRole!!.roleDescription.toString()
                )


                viewModelScope.launch {
                    rolePermissionRepository.deleteRole(role = roleEntity)
                    clearState()
                }

            }

            is RolePermissionAction.OnSelectedTabChanged -> {
                _state.update {
                    it.copy(selectedTab = action.selectedTab)
                }

            }
        }

    }

    fun loadRoles() {
        viewModelScope.launch {
            val roles = rolePermissionRepository.getRoles().collect { roles ->

                _state.update {
                    it.copy(rolePermission = roles)
                }
            }
        }

    }

    fun clearState() {
        _state.update {
            it.copy(
                selectedRole = null,
                roleName = "",
                roleDescription = "",
                roleNameError = null,
                roleDescriptionError = null,
                permissionError = null,
                permissionChecked = _state.value.permissionChecked.map { state ->
                    state.copy(isChecked = false)
                }.toMutableList()
            )
        }
    }


}