package com.techcult.salesman.feature.supplier.data

import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.Result
import com.techcult.salesman.feature.supplier.data.mapper.toSupplier
import com.techcult.salesman.feature.supplier.data.mapper.toSupplierEntity
import com.techcult.salesman.feature.supplier.domain.model.Supplier
import com.techcult.salesman.feature.supplier.domain.repository.SupplierRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SupplierRepoImpl(val supplierDao: SupplierDao) : SupplierRepository {
    override suspend fun searchSuppliers(query: String): Flow<List<Supplier>> {
        return supplierDao.searchSuppliers(query).map { suppliers ->
            suppliers.map { supplierEntity ->
                supplierEntity.toSupplier()
            }
        }
    }

    override suspend fun getAllSuppliers(): Flow<List<Supplier>> {
        return supplierDao.getAllSuppliers().map { suppliers ->
            suppliers.map { supplierEntity ->
                supplierEntity.toSupplier()
            }
        }
    }

    override suspend fun getSupplierById(id: String): Supplier? {
        return supplierDao.getSupplierById(id)?.toSupplier()
    }

    override suspend fun insertOrUpdate(supplier: Supplier): Result<Unit, DataError> {
        try {
            supplierDao.insertOrUpdate(supplier.toSupplierEntity())
            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(DataError.Local.UNKNOWN)

        }
    }


}