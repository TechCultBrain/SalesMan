package com.techcult.salesman.feature.auth.data.network

import com.techcult.salesman.core.data.networking.safeCall
import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.Result
import com.techcult.salesman.feature.auth.data.dto.LoginRequestDto
import com.techcult.salesman.feature.auth.data.dto.LoginResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class KtorRemoteAuthDataSource(val httpClient: HttpClient) : RemoteAuthDataSource {


    override suspend fun loginUser(
        email: String,
        password: String
    ): Result<LoginResponseDto, DataError.NetworkError> {
        return safeCall<LoginResponseDto> {
            httpClient.post("https://api.escuelajs.co/api/v1/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(LoginRequestDto("john@mail.com", "changeme"))
            }
        }
    }

    override fun resetPassword(): Result<Unit, DataError.NetworkError> {
        return Result.Success(Unit)
    }
}