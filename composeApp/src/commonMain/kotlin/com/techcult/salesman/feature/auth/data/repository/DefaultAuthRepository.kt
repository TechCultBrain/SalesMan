package com.techcult.salesman.feature.auth.data.repository

import androidx.datastore.preferences.core.Preferences
import com.techcult.salesman.core.data.database.UserDao
import com.techcult.salesman.core.data.database.UserEntity
import com.techcult.salesman.core.data.prefernces.PreferenceManger
import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.Error
import com.techcult.salesman.core.domain.Result
import com.techcult.salesman.core.domain.map
import com.techcult.salesman.feature.auth.data.network.RemoteAuthDataSource
import com.techcult.salesman.feature.auth.domain.repository.AuthRepository

class DefaultAuthRepository(
    val remoteAuthDataSource: RemoteAuthDataSource,
    val dao: UserDao,
    val preferenceDataSource: PreferenceManger
) :
    AuthRepository {

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
        return Result.Success(Unit)
    }

    override suspend fun logoutUser(): Result<Unit, Error> {
        preferenceDataSource.saveLoggedId("")
        return Result.Success(Unit)
    }

    override suspend fun userLogin(
        userName: String,
        password: String
    ): Result<Unit, DataError> {

        val user = dao.getUserByName(userName)
        if (user == null) {
            return Result.Error(DataError.Validation.USER_NOT_FOUND)
        }
        return if (user.password != password) {
            Result.Error(DataError.Validation.INVALID_PASSWORD)
        } else {
            preferenceDataSource.saveLoggedId(user.id.toString())
            Result.Success(Unit)
        }


    }
}