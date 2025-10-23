package com.techcult.salesman.feature.supplier.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SupplierDao {

    @Query("SELECT * FROM supplier ORDER BY supplierName ASC")
     fun getAllSuppliers(): Flow<List<SupplierEntity>>

    @Query("SELECT * FROM supplier WHERE supplierId = :id LIMIT 1")
     suspend fun getSupplierById(id: String): SupplierEntity?

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertOrUpdate(supplier: SupplierEntity)

   @Query("SELECT * FROM supplier WHERE supplierName LIKE '%' || :query || '%' OR supplierCode LIKE '%' || :query || '%'")
    fun searchSuppliers(query: String): Flow<List<SupplierEntity>>
}