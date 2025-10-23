package com.techcult.salesman.feature.product.domain.model

import kotlinx.datetime.LocalDateTime

data class Product(
    val productId: Long?,
    val productCode: String,
    val productSKU: String,
    val productName: String,
    val categoryId: Long,
    val categoryName: String,
    val productStatus: String,
    val description: String,
    val barcode: String? = null,
    val price: Double,
    val quantity: Int=0,
    val brandId: Long,
    val uomId: Long,
    val uomCode: String,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
)
