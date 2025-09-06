package com.techcult.salesman.feature.user.domain

import com.techcult.salesman.core.domain.Error
import com.techcult.salesman.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUserList(): Result<Flow<List<User>>, Error>
}