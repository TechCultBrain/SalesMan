package com.techcult.salesman.core.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map


@Dao
interface PermissionDao {


    @Insert
    suspend fun insertPermission(permission: List<PermissionEntity>)

    @Query("SELECT * FROM permissionentity")
    suspend fun getAllPermissions(): List<PermissionEntity>

    @Query("SELECT COUNT(*) FROM permissionentity")
    suspend fun countPermissions(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRole(role: RoleEntity): Long

    @Update
    suspend fun updateRole(role: RoleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRolePermissionCrossRef(crossRef: RolePermissionEntity)

    @Query("DELETE FROM role_permission_cross_ref WHERE roleId = :roleId")
    suspend fun deleteRolePermissions(roleId: Long)

    // Delete only the role row
    @Delete
    suspend fun deleteRole(role: RoleEntity)

    // Delete mappings (role ↔ permissions)


    // Transactional delete: role + mappings
    @Transaction
    suspend fun deleteRoleWithPermissions(role: RoleEntity) {
        deleteRolePermissions(role.roleId)
        deleteRole(role)
    }

    @Transaction
    suspend fun insertRoleWithPermissions(
        role: RoleEntity,
        permissions: List<PermissionEntity>
    ) {
        // 1. Insert role
        val roleId = insertRole(role)

        // 2. Insert permissions + cross refs
        for (perm in permissions) {

            insertRolePermissionCrossRef(
                RolePermissionEntity(roleId = roleId, permissionId = perm.permissionId)
            )
        }
    }
    @Transaction
    suspend fun updateRolePermissions(
        roleId: Long,
        newPermissions: List<String>
    ) {
        // 1. Remove old permissions
        deleteRolePermissions(roleId)

        // 2. Insert new  cross refs
        for (perm in newPermissions) {
            insertRolePermissionCrossRef(
                RolePermissionEntity(roleId = roleId, permissionId = perm)
            )
        }
    }


    @Transaction
    @Query("SELECT * FROM roleentity")
    fun getAllRolesWithPermissionsFlow(): Flow<List<RoleWithPermissions>>

   suspend fun getAllRolesWithPermissionIdsFlow(): Flow<List<RolePermissionId>> {
       return getAllRolesWithPermissionsFlow().map { roleWithPermsList ->
           roleWithPermsList.map { roleWithPerm ->
               RolePermissionId(
                   roleId = roleWithPerm.role.roleId,
                   roleName = roleWithPerm.role.roleName,
                   roleDescription = roleWithPerm.role.roleDescription,
                   permissionId = roleWithPerm.permissions.map { it.permissionId }
               )
           }
       }
    }

}