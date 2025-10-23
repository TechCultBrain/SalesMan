package com.techcult.salesman.feature.discount.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DiscountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(discount: DiscountEntity)

    @Update
    suspend fun update(discount: DiscountEntity)

    @Query("DELETE FROM discounts WHERE id = :discountId")
    suspend fun delete(discountId: String)

    @Query("SELECT * FROM discounts WHERE id = :discountId LIMIT 1")
    suspend fun getById(discountId: String): DiscountEntity?

    @Query("SELECT * FROM discounts ORDER BY name ASC")
    suspend fun getAll(): List<DiscountEntity>

    @Query("""
        SELECT * FROM discounts 
        WHERE isActive = 1 
        AND (startDate IS NULL OR startDate <= :today)
        AND (endDate IS NULL OR endDate >= :today)
    """)
     fun getActiveDiscounts(today: String): Flow<List<DiscountEntity>>

    @Query("SELECT * FROM discounts WHERE couponCode = :code LIMIT 1")
    suspend fun getDiscountByCoupon(code: String): DiscountEntity?
}