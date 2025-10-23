package com.techcult.salesman.feature.customer.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface CustomerDao {


    @Query("SELECT * FROM customer WHERE customerId = :id")
    suspend fun getCustomerById(id: Long): CustomerEntity?

    @Query("SELECT * FROM customer")
    fun getAllCustomers(): Flow<List<CustomerEntity>>

    @Query("SELECT * FROM customer WHERE customerName LIKE '%' || :query || '%' OR customerCode LIKE '%' || :query || '%'")
    fun searchCustomers(query: String): Flow<List<CustomerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(customer: CustomerEntity)
}