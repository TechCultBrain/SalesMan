package com.techcult.salesman.feature.product.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Entity(tableName = "products")
data class ProductEntity @OptIn(ExperimentalTime::class) constructor(
    @PrimaryKey val productId: Long? = 0,    // UUID
    val productName: String,
    val productDescription: String? = null,
    val productImage: String? = null,
    val productCode: String? = null,
    val hsnCode: String? = null,
    val productBarcode: String? = null,
    val productTags: String? = null,
    val categoryId: Long,               // FK to CategoryEntity
    val brandId: Long? = null,
    val baseUomId: Long,// FK to BrandEntity,
    val mrpMode: String = "FIXED",//FIXED OR MULTIPLE
    val batchTracking: Boolean = false,
    val stockTracking: Boolean = false,
    val defaultMRP: Double? = null,                // Used only for fixed MRP pricing
    val reorderLevel: Double = 0.0,
    // Textile / Variant specific
    val hasVariants: Boolean = false,     // if true, use ProductVariantEntity
    // Audit / sync
    val createdAt: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    val updatedAt: LocalDateTime? = null,
    val createdBy: Long? = null,
    val updatedBy: Long? = null,
    val syncStatus: String = "LOCAL",
    val active: Boolean = true,

)
