package com.techcult.salesman.feature.product.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Entity(tableName = "products")
data class ProductEntity @OptIn(ExperimentalTime::class) constructor(
    @PrimaryKey val productId: Long = 0,    // UUID
    val categoryId: Long,               // FK to CategoryEntity
    val productName: String,
    val productType: String,
    val productStatus: String,
    val productImage: String? = null,
    val productCode: String? = null,      // SKU, barcode, PLU
    val description: String? = null,
    val brandId: Long,            // FK to BrandEntity,

    // Pricing
    val mrp: Double = 0.0,                // maximum retail price
    val sellingPrice: Double = 0.0,       // default selling price
    val wholesalePrice: Double? = null,   // if wholesale customers exist

    // Stock
    val baseUomId: Long,             // pcs, kg, litre, metre
    val trackInventory: Boolean = true,
    val openingStock: Double = 0.0,
    val reorderLevel: Double = 0.0,

    // Textile / Variant specific
    val hasVariants: Boolean = false,     // if true, use ProductVariantEntity
    val expiryDate: Long? = null,         // expiry support for food/medicine

    // Audit / sync
    val createdAt: Long = Clock.System.now().epochSeconds,
    val updatedAt: Long = Clock.System.now().epochSeconds,
    val createdBy: Long?=null,
    val updatedBy: Long? = null,
    val syncStatus: String = "LOCAL",

)
