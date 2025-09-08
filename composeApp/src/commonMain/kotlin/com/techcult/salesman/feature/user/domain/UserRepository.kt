package com.techcult.salesman.feature.user.domain

import com.techcult.salesman.core.data.database.UserEntity
import com.techcult.salesman.core.data.database.UserWithRole
import com.techcult.salesman.core.domain.Error
import com.techcult.salesman.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {


    suspend fun createUser(user: UserEntity): Result<Unit, Error>

    suspend fun updateUser(user: UserEntity): Result<Unit, Error>
    suspend fun deleteUser(user: UserEntity): Result<Unit, Error>
    suspend fun getUserList(): Result<Flow<List<UserWithRole>>, Error>
    suspend fun getUserByUserName(query: String): Result<Flow<List<UserWithRole>>, Error>

}