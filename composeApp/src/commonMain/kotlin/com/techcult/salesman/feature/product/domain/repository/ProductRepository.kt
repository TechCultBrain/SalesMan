package com.techcult.salesman.feature.product.domain.repository

import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.Result
import com.techcult.salesman.feature.product.data.entity.ProductEntity
import com.techcult.salesman.feature.product.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getAllProducts(): Flow<List<Product>>
    suspend fun getProductById(productId: Long): Product?
    suspend fun searchProducts(query: String): Flow<List<Product>>
    suspend fun insertOrUpdateProduct(product: Product): Result<Unit, DataError>
}