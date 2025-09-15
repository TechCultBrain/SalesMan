package com.techcult.salesman.feature.product.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.techcult.salesman.feature.product.data.entity.BrandEntity


@Dao
interface BrandDao {

    @Query("SELECT * FROM brand")
     fun getAllBrands(): List<BrandEntity>

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertBrand(brand: BrandEntity)




}