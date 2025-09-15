package com.techcult.salesman.feature.product.domain

data class Product(
    val productId: Long,
    val productCode: String,
    val productName: String,
    val productType: String,
    val productStatus: String,
    val description: String,
    val barcode: String?=null,
    val price: Double,
    val quantity: Int,
    val category: Long,
    val brandId: Long,
    val uom: Long,
    val createdAt: Long,
    val updatedAt: Long
)
