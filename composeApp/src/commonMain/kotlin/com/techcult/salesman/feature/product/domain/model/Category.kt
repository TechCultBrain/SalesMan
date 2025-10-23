package com.techcult.salesman.feature.product.domain.model

import kotlinx.datetime.LocalDateTime

data class Category(
    val categoryId: Long?,
    val categoryName: String,
    val categoryDescription: String?,
    val categoryImage: String?,
    val description: String?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
    val active: Boolean,
    val topCategoryId: Long?=null,
)
