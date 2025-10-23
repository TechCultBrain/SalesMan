package com.techcult.salesman.feature.product.data.repository

import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.Result
import com.techcult.salesman.feature.product.data.dao.ProductDao
import com.techcult.salesman.feature.product.data.entity.ProductEntity
import com.techcult.salesman.feature.product.data.mappers.toProduct
import com.techcult.salesman.feature.product.data.mappers.toProductEntity
import com.techcult.salesman.feature.product.domain.model.Product
import com.techcult.salesman.feature.product.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.map

class ProductRepoImpl(val productDao: ProductDao) : ProductRepository {
    override suspend fun getAllProducts(): Flow<List<Product>> {
       return productDao.getProductsWithCategoryAndUom().map { products ->
           products.map { productEntity ->
               productEntity.toProduct()
           }
       }
    }

    override suspend fun getProductById(productId: Long): Product? {
        return productDao.getProductsByIdWithCategoryAndUom(productId)?.toProduct()

    }

    override suspend fun searchProducts(query: String): Flow<List<Product>> {
        return productDao.searchProducts(query).map { products ->
            products.map { productEntity ->
                productEntity.toProduct()
            }
        }
    }

    override suspend fun insertOrUpdateProduct(product: Product): Result<Unit, DataError> {
        try {
            productDao.insertProduct(product.toProductEntity())
            return Result.Success(Unit)
        }
        catch (e:Exception){
            return Result.Error(DataError.Local.UNKNOWN)


        }

    }
}