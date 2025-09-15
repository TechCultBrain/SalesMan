package com.techcult.salesman.feature.product.domain




data class Brand(
    val brandId: Long,
    val brandName: String,
    val description: String,
    val createdAt: Long,
    val updatedAt: Long,
    val active: Boolean
)
