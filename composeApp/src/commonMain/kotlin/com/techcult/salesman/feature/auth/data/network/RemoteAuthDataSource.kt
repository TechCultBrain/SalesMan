package com.techcult.salesman.feature.auth.data.network

import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.Result
import com.techcult.salesman.feature.auth.data.dto.LoginResponseDto

interface RemoteAuthDataSource {


    suspend fun loginUser(
        email: String,
        password: String
    ): Result<LoginResponseDto, DataError.NetworkError>

    fun resetPassword(): Result<Unit, DataError.NetworkError>
}