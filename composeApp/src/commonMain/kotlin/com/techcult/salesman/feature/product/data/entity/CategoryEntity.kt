package com.techcult.salesman.feature.product.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Entity(tableName = "categories")
data class CategoryEntity @OptIn(ExperimentalTime::class) constructor(
    @PrimaryKey(autoGenerate = true) val categoryId: Long = 0,
    val categoryName: String,
    val parentCategoryId: Long? = null,
    val description: String?=null,
    val createdAt: Long = Clock.System.now().epochSeconds,
    val updatedAt: Long = Clock.System.now().epochSeconds,
    val syncStatus: String = "LOCAL"
)
