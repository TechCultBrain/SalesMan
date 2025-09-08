package com.techcult.salesman.feature.user.data

import com.techcult.salesman.core.data.database.UserDao
import com.techcult.salesman.core.data.database.UserEntity
import com.techcult.salesman.core.data.database.UserWithRole
import com.techcult.salesman.core.domain.Error
import com.techcult.salesman.core.domain.Result
import com.techcult.salesman.feature.user.domain.User
import com.techcult.salesman.feature.user.domain.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(val userDao: UserDao): UserRepository {
    override suspend fun createUser(user: UserEntity): Result<Unit, Error> {
        userDao.insertUser(user)
        return Result.Success(Unit)
    }

    override suspend fun updateUser(user: UserEntity): Result<Unit, Error> {
       userDao.updateUser(user)
        return Result.Success(Unit)
    }

    override suspend fun deleteUser(user: UserEntity): Result<Unit, Error> {
       userDao.deleteUser(user)
        return Result.Success(Unit)
    }

    override suspend fun getUserList(): Result<Flow<List<UserWithRole>>, Error> {
       return Result.Success(userDao.getUser())
    }

    override suspend fun getUserByUserName(query: String): Result<Flow<List<UserWithRole>>, Error> {
        return Result.Success(userDao.getUserByUserName(query))
    }
}