package com.techcult.salesman.feature.Settings.store.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface StoreInfoDao {

 @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertStoreInfo(storeInfo: StoreInfoEntity)

    @Query("SELECT * FROM store_info")
     fun getLocalStoreInfo(): Flow<StoreInfoEntity?>

    @Update
    suspend fun updateStoreInfo(storeInfo: StoreInfoEntity)

    @Query("SELECT COUNT(*) FROM store_info")
    suspend fun checkStoreInfo(): Int




}