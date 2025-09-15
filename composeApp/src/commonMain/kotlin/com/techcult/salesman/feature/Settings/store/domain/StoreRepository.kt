package com.techcult.salesman.feature.Settings.store.domain

import com.techcult.salesman.core.domain.DataError
import kotlinx.coroutines.flow.Flow

interface StoreRepository {

    suspend fun checkStoreInfo(): com.techcult.salesman.core.domain.Result<Int, DataError>

    suspend fun saveStoreInfo(storeInfo: StoreInfo): com.techcult.salesman.core.domain.Result<String, DataError>
    suspend fun getStoreInfo(): Flow<StoreInfo?>
    suspend fun updateStoreInfo(storeInfo: StoreInfo): com.techcult.salesman.core.domain.Result<String, DataError>


}