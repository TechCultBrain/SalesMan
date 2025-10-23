package com.techcult.salesman.feature.product.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.ExperimentalTime

@Entity(tableName = "product_variants")
data class ProductVariantEntity @OptIn(ExperimentalTime::class) constructor(
    @PrimaryKey val variantId: Long = 0,    // UUID
    val productId: Long,                // FK to ProductEntity
    val size: String? = null,
    val color: String? = null,
    val style: String? = null,
    val design: String? = null,
    val barcode: String? = null

)


@Entity
data class VariantStock(
    @PrimaryKey val stockId: String,
    val productId: String,
    val variantId: String?,
    val quantity: Double          // meters or pcs
)