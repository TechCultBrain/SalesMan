package com.techcult.salesman.core.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


@Entity
data class UserEntity @OptIn(ExperimentalTime::class) constructor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userName: String,
    val email: String?,
    val password: String,
    val profilePicture: String? = null,
    val phoneNumber: String? = null,
    val address: String? = null,
    val role: Long? = null,
    val createdAt: Long = Clock.System.now().epochSeconds,
    val updatedAt: Long =Clock.System.now().epochSeconds,
    val isAvailable: Boolean = true
)
