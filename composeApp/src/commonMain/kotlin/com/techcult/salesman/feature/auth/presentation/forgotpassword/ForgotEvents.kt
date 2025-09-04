package com.techcult.salesman.feature.auth.presentation.forgotpassword

sealed interface ForgotEvents {

    object OnSendEmailSuccess : ForgotEvents
    object OnSendEmailFailure : ForgotEvents
}