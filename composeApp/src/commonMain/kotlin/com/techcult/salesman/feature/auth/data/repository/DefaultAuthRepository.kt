package com.techcult.salesman.feature.auth.data.repository

import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.Error
import com.techcult.salesman.core.domain.Result
import com.techcult.salesman.core.domain.map
import com.techcult.salesman.feature.auth.data.network.RemoteAuthDataSource
import com.techcult.salesman.feature.auth.domain.repository.AuthRepository

class DefaultAuthRepository(val remoteAuthDataSource: RemoteAuthDataSource) : AuthRepository {

    override suspend fun resetPassword(newPassword: String): Result<Unit, DataError> {

        return remoteAuthDataSource.resetPassword()

    }

    override suspend fun loginUser(
        email: String,
        password: String
    ): Result<String, DataError> {
        return remoteAuthDataSource.loginUser(email, password).map {
            it.accessToken
        }

    }

    override suspend fun registerUser(
        email: String,
        password: String
    ): Result<Unit, DataError> {
        TODO("Not yet implemented")
    }

    override suspend fun logoutUser(): Result<Unit, Error> {
        TODO("Not yet implemented")
    }
}