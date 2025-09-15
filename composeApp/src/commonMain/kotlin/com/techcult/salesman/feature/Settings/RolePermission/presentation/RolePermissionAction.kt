package com.techcult.salesman.feature.Settings.RolePermission.presentation

sealed interface RolePermissionAction {

    data class OnSelectedTabChanged(val selectedTab: Int) : RolePermissionAction

    data class OnAddRoleClicked(val isRoleAddDialogVisible: Boolean) : RolePermissionAction
    data class OnRoleNameChanged(val roleName: String) : RolePermissionAction
    data class OnRoleDescriptionChanged(val roleDescription: String) : RolePermissionAction
    data class OnPermissionSelected(val permissionId: String, val isChecked: Boolean) : RolePermissionAction

    data object OnSaveClicked:RolePermissionAction
    data class OnViewRoleClicked(val isRoleDetailDialogVisible: Boolean,val id: Long) : RolePermissionAction
    data class OnEditRoleClicked(val isRoleAddDialogVisible: Boolean,val id: Long) : RolePermissionAction

    data object OnDeleteRoleClicked:RolePermissionAction
}