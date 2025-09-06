package com.techcult.salesman.feature.RolePermission.data

import com.techcult.salesman.core.data.database.AppDatabase
import com.techcult.salesman.core.data.database.PermissionEntity
import com.techcult.salesman.core.data.database.RoleEntity
import com.techcult.salesman.core.data.database.RolePermissionId
import com.techcult.salesman.feature.RolePermission.domain.RolePermissionRepository
import kotlinx.coroutines.flow.Flow

class RolePermissionRepoImpl(val db: AppDatabase) : RolePermissionRepository {
    override suspend fun insertPermissions(permissions: List<PermissionEntity>) {
        db.permissionDao().insertPermission(permissions)
    }

    override suspend fun countPermissions(): Int {
        return db.permissionDao().countPermissions()
    }

    override suspend fun getPermissions(): List<PermissionEntity> {
        return db.permissionDao().getAllPermissions()
    }

    override suspend fun createRole(
        roleName: String,
        roleDescription: String,
        permissionIds: List<String>
    ) {
        val roleId = db.permissionDao().insertRole(
            com.techcult.salesman.core.data.database.RoleEntity(
                roleName = roleName,
                roleDescription = roleDescription
            )
        )
        for (permissionId in permissionIds) {
            db.permissionDao().insertRolePermissionCrossRef(
                com.techcult.salesman.core.data.database.RolePermissionEntity(
                    roleId = roleId,
                    permissionId = permissionId
                )
            )
        }
    }

    override suspend fun updateRole(
        roleId: Long,
        roleName: String,
        roleDescription: String,
        permissionIds: List<String>
    ) {
        db.permissionDao().updateRole(
            com.techcult.salesman.core.data.database.RoleEntity(
                roleId = roleId,
                roleName = roleName,
                roleDescription = roleDescription
            )
        )

        db.permissionDao().updateRolePermissions(roleId, permissionIds)


    }

    override suspend fun getRoles(): Flow<List<RolePermissionId>> {
        return db.permissionDao().getAllRolesWithPermissionIdsFlow()

    }

    override suspend fun deleteRole(role: RoleEntity) {
        db.permissionDao().deleteRoleWithPermissions(role)
    }
}