package com.techcult.salesman.feature.supplier.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SupplierDao {

    @Query("SELECT * FROM supplier ORDER BY supplierName ASC")
     fun getAllSuppliers(): List<SupplierEntity>

    @Query("SELECT * FROM supplier WHERE supplierId = :id LIMIT 1")
     fun getSupplierById(id: String): SupplierEntity?

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertOrUpdate(supplier: SupplierEntity)

    @Delete
    suspend fun deleteSupplier(supplier: SupplierEntity)
}