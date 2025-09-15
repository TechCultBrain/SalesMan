package com.techcult.salesman.feature.auth.domain.repository

import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.Error
import com.techcult.salesman.core.domain.Result

interface AuthRepository {

    suspend fun resetPassword(newPassword: String): Result<Unit, DataError>

    suspend fun loginUser(email: String, password: String): Result<String, DataError>

    suspend fun registerUser(email: String, password: String): Result<Unit, DataError>

    suspend fun logoutUser(): Result<Unit, Error>

    suspend fun userLogin(userName: String, password: String): Result<Unit, DataError>

}