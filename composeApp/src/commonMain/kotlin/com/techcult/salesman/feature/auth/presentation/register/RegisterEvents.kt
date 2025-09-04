package com.techcult.salesman.feature.auth.presentation.register

interface RegisterEvents {

    object onRegisterSucces: RegisterEvents
    data class onRegisterFailed(val message: String): RegisterEvents
}