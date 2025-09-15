package com.techcult.salesman.feature.uom.data

import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.Error
import com.techcult.salesman.core.domain.Result
import com.techcult.salesman.feature.uom.domain.Uom
import com.techcult.salesman.feature.uom.domain.UomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UomRepositoryImpl(val uomDao: UomDao) : UomRepository {
    override suspend fun getAllUom(): Flow<List<Uom>> {
        return uomDao.getAllUom().map { uomEntities ->
            uomEntities.map { uomEntity ->
                uomEntity.toUom()
            }
        }
    }

    override suspend fun getUomById(id: Long): Uom? {
        return uomDao.getUomById(id)?.toUom()
    }


    override suspend fun searchUom(query: String): Flow<List<Uom>> {
        return uomDao.searchUom(query).map { uomEntities ->
            uomEntities.map { uomEntity ->
                uomEntity.toUom()

            }
        }


    }

    override suspend fun insertOrUpdate(uom: Uom): Result<Unit, Error> {
        try {
            uomDao.insertOrUpdate(uom.toUomEntity())
            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(DataError.Local.UNKNOWN)
        }
    }

    override suspend fun deleteUom(uom: Uom): Result<Unit, Error> {
        try {
            uomDao.deleteUom(uom.toUomEntity())
            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(DataError.Local.UNKNOWN)
        }
    }

}