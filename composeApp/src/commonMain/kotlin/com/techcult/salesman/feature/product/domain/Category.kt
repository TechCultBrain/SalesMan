package com.techcult.salesman.feature.product.domain

data class Category(
    val categoryId: Long,
    val categoryName: String,
    val description: String,
    val createdAt: Long,
    val updatedAt: Long,
    val active: Boolean
)
