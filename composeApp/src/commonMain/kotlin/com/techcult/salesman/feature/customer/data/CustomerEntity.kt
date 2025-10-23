package com.techcult.salesman.feature.customer.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "customer")
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true) val customerId: Long = 0,
    val customerCode: String?,
    val customerName: String?,
    val customerAddress: String?,
    val customerPhone: String?,
    val customerStatus: String?,
    val customerType: String?,
    val customerContactNo: String?,
    val customerContactName: String?,
    val customerContactEmail: String?,
    val customerCity: String?,
    val customerState: String?,
    val customerZipCode: String?,
    val customerTin: String?,
    val panNumber: String?,
    val customerGst: String?,
    val creditLimit: Double,
    val creditDays: Int,
    val notes: String?,
    val createdAT: Long,
    val updatedAT: Long?,
)
