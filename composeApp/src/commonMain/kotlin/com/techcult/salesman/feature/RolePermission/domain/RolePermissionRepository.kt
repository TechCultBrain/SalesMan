package com.techcult.salesman.feature.RolePermission.domain

import com.techcult.salesman.core.data.database.PermissionEntity
import com.techcult.salesman.core.data.database.RoleEntity
import com.techcult.salesman.core.data.database.RolePermissionId
import kotlinx.coroutines.flow.Flow

interface RolePermissionRepository {


    suspend fun insertPermissions(permissions: List<PermissionEntity>)
    suspend fun countPermissions(): Int
    suspend fun getPermissions(): List<PermissionEntity>

    suspend fun createRole(roleName: String, roleDescription: String, permissionIds: List<String>)

    suspend fun updateRole(roleId: Long, roleName: String, roleDescription: String, permissionIds: List<String>)
    suspend fun getRoles(): Flow<List<RolePermissionId>>

    suspend fun deleteRole(role: RoleEntity)
}