package com.techcult.salesman.feature.product.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Entity(tableName = "brand")
data class BrandEntity @OptIn(ExperimentalTime::class) constructor(
    @PrimaryKey(autoGenerate = true) val brandId: Long? = 0,
    val brandName: String,
    val brandCode: String? = null,
    val description: String? = null,
    val logo: String? = null,
    val available: Boolean = true,
    val createdAt: LocalDateTime = Clock.System.now()
        .toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault()),
    val updatedAt: LocalDateTime? = null,
    val syncStatus: String = "LOCAL"
)

