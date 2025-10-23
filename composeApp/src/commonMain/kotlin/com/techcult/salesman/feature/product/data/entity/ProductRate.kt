package com.techcult.salesman.feature.product.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime


@Entity
data class ProductRate(
    @PrimaryKey val rateId: Long? = 0,
    val productId: Long,
    val variantId: Long?,
    val batchId: Long? = null,
    val uomId: Long,// used only if MULTIPLE MRP
    val priceType: String,     // Retail, Wholesale
    val sellingPrice: Double,
    val taxInclusive: Boolean,// system price (may equal MRP or less)
    val taxId: String?,
    val effectiveDate: LocalDateTime
)
