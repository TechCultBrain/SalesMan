package com.techcult.salesman.feature.Settings.store.data.repository

import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.Result
import com.techcult.salesman.feature.Settings.store.data.database.StoreInfoDao
import com.techcult.salesman.feature.Settings.store.data.mapper.toStoreInfo
import com.techcult.salesman.feature.Settings.store.data.mapper.toStoreInfoEntity
import com.techcult.salesman.feature.Settings.store.domain.StoreInfo
import com.techcult.salesman.feature.Settings.store.domain.StoreRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class DefaultStoreInfoRepo(val storeInfoDao: StoreInfoDao) : StoreRepository {
    override suspend fun checkStoreInfo(): Result<Int, DataError> {
        try {
            val count = storeInfoDao.checkStoreInfo()
            return Result.Success(count)
        } catch (e: Exception) {
            return Result.Error(DataError.Local.UNKNOWN)
        }

    }

    override suspend fun saveStoreInfo(storeInfo: StoreInfo): com.techcult.salesman.core.domain.Result<String, DataError> {

        try {
            storeInfoDao.insertStoreInfo(storeInfo.toStoreInfoEntity())
            return com.techcult.salesman.core.domain.Result.Success("Store info saved successfully")
        } catch (e: Exception) {
            return com.techcult.salesman.core.domain.Result.Error(DataError.Local.UNKNOWN)
        }

    }

    override suspend fun getStoreInfo(): Flow<StoreInfo?> {

        val localStoreInfo = storeInfoDao.getLocalStoreInfo()
        return localStoreInfo.map { it?.toStoreInfo(it) }



    }

    override suspend fun updateStoreInfo(storeInfo: StoreInfo): com.techcult.salesman.core.domain.Result<String, DataError> {
        try {
            storeInfoDao.updateStoreInfo(storeInfo.toStoreInfoEntity())
            return com.techcult.salesman.core.domain.Result.Success("Store info updated successfully")
        } catch (e: Exception) {
            return com.techcult.salesman.core.domain.Result.Error(DataError.Local.UNKNOWN)
        }
    }


}