package com.techcult.salesman.feature.uom.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UomDao {

    @Query("SELECT * FROM uoms ORDER BY name ASC")
    fun getAllUom(): Flow<List<UomEntity>>

    @Query("SELECT * FROM uoms WHERE uomId = :id")
    suspend fun getUomById(id: Long): UomEntity?


    @Query("SELECT * FROM uoms WHERE name LIKE '%' || :query || '%' OR symbol LIKE '%' || :query || '%' ORDER BY name ASC")
    fun searchUom(query: String): Flow<List<UomEntity>>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertOrUpdate(uom: UomEntity)

    @Delete
    suspend fun deleteUom(uom: UomEntity)

}