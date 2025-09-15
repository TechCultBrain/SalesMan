package com.techcult.salesman.feature.purchase.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "purchase_items",

)
data class PurchaseItemEntity(
    @PrimaryKey val itemId: String,   // UUID
    val purchaseId: Long,           // FK to PurchaseEntity
    val productId: String,            // reference to ProductEntity
    val quantity: Double,
    val unitPrice: Double,
    val discount: Double = 0.0,
    val taxPercent: Double = 0.0,
    val total: Double                 // (qty * unitPrice - discount) + tax
)