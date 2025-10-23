package com.techcult.salesman.feature.product.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.techcult.salesman.feature.product.data.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories WHERE categoryName LIKE '%' || :query || '%' ORDER BY categoryName ASC")
    fun searchCategories(query: String): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM categories ORDER BY categoryName ASC")
    fun getAllCategories(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM categories WHERE categoryId = :id")
    suspend fun getCategoryById(id: Long): CategoryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(category: CategoryEntity)


}