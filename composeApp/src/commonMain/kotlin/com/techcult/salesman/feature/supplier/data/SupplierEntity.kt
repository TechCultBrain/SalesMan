package com.techcult.salesman.feature.supplier.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.Clock
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Entity(tableName = "supplier")
data class SupplierEntity(
    @PrimaryKey(autoGenerate = true) val supplierId: Long = 0,
    val supplierCode: String?,
    val supplierName: String?,
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
    val createdAT: Long= Clock.System.now().epochSeconds,
    val updatedAT: Long= Clock.System.now().epochSeconds,
 )