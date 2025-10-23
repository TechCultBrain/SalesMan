package com.techcult.salesman.feature.product.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity
data class OpeningStock(
    @PrimaryKey(autoGenerate = true) val openingStockId: Long? = 0,
    val productId: Long,
    val variantId: Long? = null,
    val batchId: Long? = null,
    val quantity: Double,
    val costPrice: Double,
    val mrp: Double? = null,
    val effectiveDate: LocalDateTime
)
