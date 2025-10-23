package com.techcult.salesman.feature.product.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchaseBatch")
data class PurchaseBatchEntity(
    @PrimaryKey(autoGenerate = true) val purchaseBatchId: Long? = 0,
    val batchNumber: String,
    val variantId: Long? = null,
    val purchaseOrderId: Long?,
    val mrp: Double?, // NULL if product is FIXED-MRP
    val purchasePrice: Double,
    val quantity: Double,
    val supplierId: String,
    val expiryDate: Long? = null
)