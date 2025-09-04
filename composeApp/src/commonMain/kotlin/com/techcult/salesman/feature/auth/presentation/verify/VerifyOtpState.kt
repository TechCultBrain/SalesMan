package com.techcult.salesman.feature.auth.presentation.verify

data class VerifyOtpState(
    val code: List<Int?> = (1..4).map { null },
    val focusedIndex: Int? = null,
    val isValid: Boolean? = null,
    val isLoading: Boolean = false,
    val otpText: String = "",
    val initialTimeInSeconds: Int = 60,
    val timeLeft: Int = initialTimeInSeconds,
    val isRunning: Boolean = true
)
