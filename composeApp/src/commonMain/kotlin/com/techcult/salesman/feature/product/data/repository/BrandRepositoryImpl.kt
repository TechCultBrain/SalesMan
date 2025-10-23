package com.techcult.salesman.feature.product.data.repository

import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.Error
import com.techcult.salesman.core.domain.Result
import com.techcult.salesman.feature.product.data.dao.BrandDao
import com.techcult.salesman.feature.product.data.mappers.toBrand
import com.techcult.salesman.feature.product.data.mappers.toBrandEntity
import com.techcult.salesman.feature.product.domain.model.Brand
import com.techcult.salesman.feature.product.domain.repository.BrandRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BrandRepositoryImpl(private val brandDao: BrandDao) : BrandRepository {
    override suspend fun getAllBrands(): Flow<List<Brand>> {
        return brandDao.getAllBrand().map { brands ->
            brands.map { it.toBrand() }
        }

    }

    override suspend fun getBrandById(brandId: Long): Brand? {
        return brandDao.getBrandById(brandId)?.toBrand()
    }

    override suspend fun searchBrands(query: String): Flow<List<Brand>> {
        return brandDao.searchBrand(query).map { brands ->
            brands.map { it.toBrand() }
        }
    }

    override suspend fun insertOrUpdate(brand: Brand): Result<Unit, DataError> {
        try {
            brandDao.insertOrUpdate(brand.toBrandEntity())
            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(DataError.Local.UNKNOWN)

        }
    }
}