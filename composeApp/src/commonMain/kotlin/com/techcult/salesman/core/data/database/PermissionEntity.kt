package com.techcult.salesman.core.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class PermissionEntity(

    @PrimaryKey val permissionId: String,
    val permissionName: String,
    val permissionDescription: String,
    val permissionDepartment: String
)
