package com.techcult.salesman.feature.product.domain.model

import kotlinx.datetime.LocalDateTime


data class Brand(
    val brandId: Long?,
    val brandName: String,
    val brandCode: String,
    val description: String?,
    val image: String?,
    val isActive: Boolean=true,
    val createdAt: LocalDateTime?=null,
    val updatedAt: LocalDateTime?=null,
)