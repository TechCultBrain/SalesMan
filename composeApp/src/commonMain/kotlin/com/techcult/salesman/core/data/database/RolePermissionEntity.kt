package com.techcult.salesman.core.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "role_permission_cross_ref",
    primaryKeys = ["roleId", "permissionId"])
data class RolePermissionEntity(
    val roleId: Long,
    val permissionId: String
)
