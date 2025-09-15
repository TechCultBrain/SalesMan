package com.techcult.salesman.feature.product.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Entity(tableName = "product_variants")
data class ProductVariantEntity @OptIn(ExperimentalTime::class) constructor(
    @PrimaryKey val variantId: Long=0,    // UUID
    val productId: Long,                // FK to ProductEntity
    val size: String? = null,             // e.g., M, L, XL
    val color: String? = null,            // e.g., Red, Blue
    val batchNumber: String? = null,
    val expiryDate: Long? = null,
    val mrp: Double = 0.0,
    val sellingPrice: Double = 0.0,
    val wholesalePrice: Double? = null,
    val stockQty: Double = 0.0,
    val uomId: Long,
    val createdAt: Long = Clock.System.now().epochSeconds,
    val updatedAt: Long = Clock.System.now().epochSeconds,
    val syncStatus: String = "LOCAL",
    val createdBy: Long?,
    val updatedBy: Long? = null,
    val trackInventory: Boolean = true,
    val openingStock: Double = 0.0,
    val reorderLevel: Double = 0.0,
    val available: Boolean = true
)

