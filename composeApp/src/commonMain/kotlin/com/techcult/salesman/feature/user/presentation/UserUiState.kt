package com.techcult.salesman.feature.user.presentation

import androidx.compose.ui.semantics.Role
import com.techcult.salesman.core.data.database.RoleEntity
import com.techcult.salesman.core.data.database.RolePermissionId
import com.techcult.salesman.core.data.database.UserWithRole


data class UserUiState(
    val isLoading: Boolean = false,
    val users: List<UserWithRole> = emptyList(),
    val roleList: List<RolePermissionId> = emptyList(),
    val userName: String?="",
    val email: String?="",
    val phoneNo: String?="",
    val password: String?="",
    val confirmPassword: String?="",
    val selectedRole: Role?=null,
    val isUserAddDialogOpen: Boolean = false,
    val isEditMode: Boolean = false,
    val userNameError:String?=null,
    val emailError:String?=null,
    val phoneNoError:String?=null,
    val passwordError:String?=null,
    val confirmPasswordError:String?=null,
    val isRoleDropOpen: Boolean = false,
    val roleName:String?=null,
    val roleId:Long?=null,
    val roleError:String?=null,
    val selectedUser:UserWithRole?=null,
    val query:String?=null
    )
