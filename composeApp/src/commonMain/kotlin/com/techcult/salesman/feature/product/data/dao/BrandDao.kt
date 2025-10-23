package com.techcult.salesman.feature.product.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.techcult.salesman.feature.product.data.entity.BrandEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BrandDao {

    @Query("SELECT * FROM brand ORDER BY brandName ASC")
    fun getAllBrand(): Flow<List<BrandEntity>>

    @Query("SELECT * FROM Brand WHERE brandId = :id")
    suspend fun getBrandById(id: Long): BrandEntity?


    @Query("SELECT * FROM Brand WHERE brandName LIKE '%' || :query || '%'  ORDER BY brandName ASC")
    fun searchBrand(query: String): Flow<List<BrandEntity>>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertOrUpdate(brand: BrandEntity)


}