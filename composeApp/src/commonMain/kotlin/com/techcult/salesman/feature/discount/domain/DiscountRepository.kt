package com.techcult.salesman.feature.discount.domain

import kotlinx.coroutines.flow.Flow

interface DiscountRepository {
    suspend fun addDiscount(discount: Discount)
    suspend fun updateDiscount(discount: Discount)
    suspend fun deleteDiscount(id: String)
    suspend fun getDiscount(id: String): Discount?
    suspend fun getAllDiscounts(): List<Discount>
     fun getActiveDiscounts(): Flow<List<Discount>>
    suspend fun findByCouponCode(code: String): Discount?
}