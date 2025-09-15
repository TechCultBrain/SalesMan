package com.techcult.salesman.feature.uom.domain

import com.techcult.salesman.core.domain.Error
import com.techcult.salesman.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface UomRepository {
    suspend fun getAllUom(): Flow<List<Uom>>
    suspend fun getUomById(id: Long): Uom?
    suspend fun searchUom(query: String): Flow<List<Uom>>
    suspend fun insertOrUpdate(uom: Uom): Result<Unit, Error>
    suspend fun deleteUom(uom: Uom): Result<Unit, Error>
}