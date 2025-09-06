package com.techcult.salesman.core.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class RoleEntity(
    @PrimaryKey(autoGenerate = true)
    val roleId: Long = 0,
    val roleName: String?,
    val roleDescription: String?
)

data class RoleWithPermissions(
    @Embedded val role: RoleEntity,
    @Relation(
        parentColumn = "roleId",
        entityColumn = "permissionId",
        associateBy = Junction(RolePermissionEntity::class)
    )
    val permissions: List<PermissionEntity>
)
data class RolePermissionId(val roleId: Long,val roleName: String?,val roleDescription: String?,val permissionId: List<String>)