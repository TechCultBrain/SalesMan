package com.techcult.salesman.feature.discount.domain

import com.techcult.salesman.feature.discount.data.DiscountEntity

fun DiscountEntity.toDomain() = Discount(
    id.toString(), name, type, value, scope,
    minPurchaseAmount, startDate, endDate, couponCode, isActive
)

fun Discount.toEntity() = DiscountEntity(
    id?.toLong(), name, type, value, scope,
    minPurchaseAmount, startDate, endDate, couponCode, isActive
)