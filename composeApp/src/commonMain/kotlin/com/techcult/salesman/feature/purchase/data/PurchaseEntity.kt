package com.techcult.salesman.feature.purchase.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


@Entity(tableName = "purchases")
data class PurchaseEntity @OptIn(ExperimentalTime::class) constructor(
    @PrimaryKey(autoGenerate = true) val purchaseId: Long = 0,
    val supplierId: Long,
    val invoiceNumber: String?,         // supplier invoice no.
    val purchaseDate: Long,             // epoch millis
    val totalAmount: Double,            // gross total
    val discount: Double = 0.0,         // discount applied
    val taxAmount: Double = 0.0,        // total tax
    val netAmount: Double,              // final amount after tax & discount
    val paymentStatus: String = "PENDING", // PENDING, PAID, PARTIAL
    val notes: String? = null,          // optional remarks
    val createdAt: Long = Clock.System.now().epochSeconds,
    val updatedAt: Long = Clock.System.now().epochSeconds,

)
