package com.techcult.salesman.feature.purchase.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface PurchaseDao {

    @Transaction
    @Query("SELECT * FROM purchases WHERE purchaseId = :id LIMIT 1")
     suspend fun getPurchaseWithItems(id: String): PurchaseWithItems?

    @Transaction
    @Query("SELECT * FROM purchases ORDER BY purchaseDate DESC")
     suspend fun getAllPurchases(): List<PurchaseWithItems>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertPurchase(purchase: PurchaseEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertItems(items: List<PurchaseItemEntity>)

    @Transaction
    suspend fun insertPurchaseWithItems(
        purchase: PurchaseEntity,
        items: List<PurchaseItemEntity>
    ) {
        insertPurchase(purchase)
        insertItems(items)
    }


}