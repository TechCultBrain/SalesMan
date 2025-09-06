package com.techcult.salesman.feature.RolePermission.presentation

import androidx.compose.runtime.MutableState
import com.techcult.salesman.core.data.database.PermissionEntity
import com.techcult.salesman.core.data.database.RolePermissionId

data class RolePermissionUiState(
    val roleName: String = "",
    val roleNameError:String?=null,
    val roleDescriptionError:String?=null,
    val roleDescription: String = "",
    val permissionError:String?=null,
    val selectedTab: Int = 0,
    val selectedRoleId: Long = 0,
    val permissions: List<PermissionEntity> = emptyList(),
    val isRoleAddDialogVisible: Boolean = false,
    val isRoleDetailDialogVisible: Boolean = false,
    val permissionChecked: MutableList<PermissionChecked> = mutableListOf(),
    val rolePermission: List<RolePermissionId> = emptyList(),
    val selectedRole: RolePermissionId? = null,
    val isDeleteOptionSelected: Boolean = false,
)


data class PermissionChecked(val permissionEntity: PermissionEntity, val isChecked: Boolean)