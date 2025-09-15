package com.techcult.salesman.feature.product.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.techcult.salesman.feature.product.data.entity.CategoryEntity


@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories")
     fun getAllCategories(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity)



}