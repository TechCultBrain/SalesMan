package com.techcult.salesman.feature.discount.data

import com.techcult.salesman.feature.discount.domain.Discount
import com.techcult.salesman.feature.discount.domain.DiscountRepository
import com.techcult.salesman.feature.discount.domain.toDomain
import com.techcult.salesman.feature.discount.domain.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class DiscountRepositoryImpl(
    private val dao: DiscountDao
) : DiscountRepository {

    override suspend fun addDiscount(discount: Discount) {
        dao.insert(discount.toEntity())
    }

    override suspend fun updateDiscount(discount: Discount) {
        dao.update(discount.toEntity())
    }

    override suspend fun deleteDiscount(id: String) {
        dao.delete(id)
    }

    override suspend fun getDiscount(id: String): Discount? {
        return dao.getById(id)?.toDomain()
    }

    override suspend fun getAllDiscounts(): List<Discount> {
        return dao.getAll().map { it.toDomain() }
    }

    @OptIn(ExperimentalTime::class)
    override  fun getActiveDiscounts(): Flow<List<Discount>> {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toString()
        return dao.getActiveDiscounts(today).map {
            it.map { entity -> entity.toDomain() }
        }
    }

    override suspend fun findByCouponCode(code: String): Discount? {
        return dao.getDiscountByCoupon(code)?.toDomain()
    }
}