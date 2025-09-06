package com.techcult.salesman.feature.user.data

import com.techcult.salesman.core.domain.Error
import com.techcult.salesman.core.domain.Result
import com.techcult.salesman.feature.user.domain.User
import com.techcult.salesman.feature.user.domain.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl: UserRepository {
    override suspend fun getUserList(): Result<Flow<List<User>>, Error> {
        TODO("Not yet implemented")
    }
}