package com.techcult.salesman.feature.supplier.domain.model

import androidx.room.PrimaryKey
import kotlin.time.Clock

data class Supplier(
    val supplierId: Long = 0,
    val supplierCode: String?,
    val supplierName: String,
    val supplierAddress: String?,
    val supplierPhone: String?,
    val supplierStatus: String?,
    val supplierType: String?,
    val supplierContactNo: String?,
    val supplierContactName: String?,
    val supplierContactEmail: String?,
    val supplierCity: String?,
    val supplierState: String?,
    val supplierZipCode: String?,
    val supplierTin: String?,
    val panNumber: String?,
    val supplierGst: String?,
    val creditLimit: Double,
    val creditDays: Int,
    val notes: String?,
)
