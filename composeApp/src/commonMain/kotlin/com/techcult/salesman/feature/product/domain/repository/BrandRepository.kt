package com.techcult.salesman.feature.product.domain.repository

import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.Error
import com.techcult.salesman.core.domain.Result
import com.techcult.salesman.feature.product.domain.model.Brand
import kotlinx.coroutines.flow.Flow

interface BrandRepository {
    suspend fun getAllBrands(): Flow<List<Brand>>
    suspend fun getBrandById(brandId: Long): Brand?
    suspend fun searchBrands(query: String): Flow<List<Brand>>
    suspend fun insertOrUpdate(brand: Brand): Result<Unit, DataError>

}