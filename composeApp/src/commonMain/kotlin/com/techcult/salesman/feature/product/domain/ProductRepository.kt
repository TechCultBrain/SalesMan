package com.techcult.salesman.feature.product.domain

import kotlinx.coroutines.flow.Flow

interface ProductRepository {


    suspend fun insertCategory(category: Category)
    suspend fun getAllProducts(): List<Product>
    suspend fun getProductById(productId: Long): Product?
    suspend fun insertProduct(product: Product)
    suspend fun updateProduct(product: Product)
    suspend fun deleteProduct(productId: Long)
    suspend fun searchProducts(query: String): Flow<List<Product>>


}