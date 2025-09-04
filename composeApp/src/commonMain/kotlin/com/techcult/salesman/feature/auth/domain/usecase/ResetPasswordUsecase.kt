package com.techcult.salesman.feature.auth.domain.usecase

import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.Result
import com.techcult.salesman.feature.auth.domain.repository.AuthRepository

class ResetPasswordUseCase(val repository: AuthRepository) {

    suspend fun execute(newPassword: String, confirmPassword: String): Result<Unit, DataError> {
        if (newPassword.isBlank() || confirmPassword.isBlank()) {
            return Result.Error(DataError.Validation.INVALID_PASSWORD)
        }
        if (newPassword.trim().toString().length < 6) {
            return Result.Error(DataError.Validation.PASSWORD_TOO_SHORT)
        }

        if (newPassword != confirmPassword) {
            return Result.Error(DataError.Validation.PASSWORD_MISMATCH)
        }
        return repository.resetPassword(newPassword)

    }
}