package com.techcult.salesman.feature.discount.domain

import com.techcult.salesman.feature.discount.data.DiscountScope
import com.techcult.salesman.feature.discount.data.DiscountType
import kotlinx.datetime.LocalDate

data class Discount(
    val id: String?,
    val name: String,
    val type: DiscountType,
    val value: Double,
    val scope: DiscountScope,
    val minPurchaseAmount: Double? = null,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val couponCode: String? = null,
    val isActive: Boolean = true
)