package com.techcult.salesman.core.utils

import com.techcult.salesman.core.domain.DataError
import com.techcult.salesman.core.domain.Error

fun errorToMessage(error: Error): String {
    return when(error)
    {
        DataError.Validation.INVALID_PASSWORD -> "Invalid Password"
        DataError.Validation.USER_NOT_FOUND -> "User Not Found"
        else -> "Something went wrong"


    }
}