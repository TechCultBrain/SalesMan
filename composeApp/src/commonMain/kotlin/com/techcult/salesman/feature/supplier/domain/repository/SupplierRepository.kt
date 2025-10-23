package com.techcult.salesman.feature.supplier.domain.repository

import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.Result
import com.techcult.salesman.feature.supplier.domain.model.Supplier
import kotlinx.coroutines.flow.Flow

interface SupplierRepository {

    suspend fun searchSuppliers(query: String): Flow<List<Supplier>>
    suspend fun getAllSuppliers(): Flow<List<Supplier>>
    suspend fun getSupplierById(id: String): Supplier?
    suspend fun insertOrUpdate(supplier: Supplier): Result<Unit, DataError>

}