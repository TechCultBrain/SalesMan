package com.techcult.salesman.feature.product.data.dao

import androidx.room.*
import com.techcult.salesman.feature.product.data.entity.ProductEntity
import com.techcult.salesman.feature.product.data.entity.ProductWithCategoryAndUom
import com.techcult.salesman.feature.product.data.entity.ProductWithVariantsAndUom
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    // ----------------
    // Insert / Update
    // ----------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertProducts(products: List<ProductEntity>)

    @Update
     suspend fun updateProduct(product: ProductEntity)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    //    // ----------------
//    // Queries
//    // ----------------
//
    @Query("SELECT * FROM products ORDER BY productName ASC")
     fun getAllProducts(): Flow<List<ProductEntity>>


    @Query("SELECT * FROM products WHERE productId = :productId LIMIT 1")
    suspend fun getProductById(productId: Long): ProductEntity?

    @Query(
        """
        SELECT * FROM products
        WHERE productName LIKE '%' || :query || '%'
           OR productCode LIKE '%' || :query || '%'
        ORDER BY productName ASC
    """
    )
     fun searchProducts(query: String): Flow<List<ProductEntity>>

    // ----------------
    // Relations
    // ----------------

    @Transaction
    @Query("SELECT * FROM products")
     fun getProductsWithCategoryAndUom(): Flow<List<ProductWithCategoryAndUom>>


    @Transaction
    @Query("SELECT * FROM products WHERE productId = :productId LIMIT 1")
     fun getProductWithVariants(productId: Long): Flow<ProductWithVariantsAndUom>
}

