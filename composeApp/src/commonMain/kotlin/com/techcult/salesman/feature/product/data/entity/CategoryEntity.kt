package com.techcult.salesman.feature.product.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Entity(tableName = "categories")
data class CategoryEntity @OptIn(ExperimentalTime::class) constructor(
    @PrimaryKey(autoGenerate = true) val categoryId: Long? = 0,
    val categoryName: String,
    val description: String?=null,
    val categoryImage: String? = null,
    val available: Boolean = true,
    val parentCategoryId: Long? = null,
    val createdAt: LocalDateTime? = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()),
    val updatedAt: LocalDateTime? = null,
    val syncStatus: String = "LOCAL"

)
