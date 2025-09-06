package com.techcult.salesman.feature.user.domain

import com.techcult.salesman.core.domain.Error
import com.techcult.salesman.core.domain.Result
import kotlinx.coroutines.flow.Flow

class GetUserListUseCase(val userRepository: UserRepository) {

    suspend fun invoke(): Result<Flow<List<User>>, Error> {
        return userRepository.getUserList()

    }
}

