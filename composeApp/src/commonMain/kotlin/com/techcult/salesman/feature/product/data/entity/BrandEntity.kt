package com.techcult.salesman.feature.product.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Entity(tableName = "brand")
data class BrandEntity @OptIn(ExperimentalTime::class) constructor(
    @PrimaryKey(autoGenerate = true) val brandId: Long = 0,
    val brandName: String,
    val description: String? = null,
    val logo: String? = null,
    val available: Boolean = true,
    val createdAt: Long = Clock.System.now().epochSeconds,
    val updatedAt: Long = Clock.System.now().epochSeconds,
    val syncStatus: String = "LOCAL"
)
