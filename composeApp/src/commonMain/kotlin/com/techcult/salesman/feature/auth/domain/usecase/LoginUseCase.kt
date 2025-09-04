package com.techcult.salesman.feature.auth.domain.usecase

import com.techcult.salesman.core.data.prefernces.PreferenceManger
import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.Error
import com.techcult.salesman.core.domain.Result
import com.techcult.salesman.feature.auth.domain.repository.AuthRepository

class LoginUseCase(val authRepository: AuthRepository, val preferenceManger: PreferenceManger) {

    suspend fun invoke(email: String, password: String): Result<String, Error> {
        if (email.isBlank() || password.isBlank()) {
            return Result.Error(DataError.Validation.INVALID_PASSWORD)
        } else if (!email.contains("@")) {
            return Result.Error(DataError.Validation.INVALID_EMAIL)
        }

        val result = authRepository.loginUser(email, password)
        return when (result) {
            is Result.Error<*> -> {
                Result.Error(result.error)
            }

            is Result.Success<*> -> {
                preferenceManger.saveAccessToken(result.data as String)
                Result.Success("Login Success")
            }
        }

    }
}