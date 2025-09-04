package com.techcult.salesman.feature.auth.di

import com.techcult.salesman.core.data.prefernces.PreferenceManger
import com.techcult.salesman.core.data.prefernces.createDataStore
import com.techcult.salesman.feature.auth.data.network.KtorRemoteAuthDataSource
import com.techcult.salesman.feature.auth.data.network.RemoteAuthDataSource
import com.techcult.salesman.feature.auth.data.repository.DefaultAuthRepository
import com.techcult.salesman.feature.auth.domain.repository.AuthRepository
import com.techcult.salesman.feature.auth.domain.usecase.LoginUseCase
import com.techcult.salesman.feature.auth.domain.usecase.ResetPasswordUseCase
import com.techcult.salesman.feature.auth.presentation.forgotpassword.ForgotPasswordViewModel
import com.techcult.salesman.feature.auth.presentation.login.LoginViewModel
import com.techcult.salesman.feature.auth.presentation.register.RegisterViewModel
import com.techcult.salesman.feature.auth.presentation.reset.ResetPasswordViewModel
import com.techcult.salesman.feature.auth.presentation.verify.VerifyOtpViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module


val authModule = module {

    viewModel { LoginViewModel(get(),get()) }
    viewModel { RegisterViewModel() }
    viewModel { ForgotPasswordViewModel() }
    viewModel { VerifyOtpViewModel() }
    viewModel { ResetPasswordViewModel(get()) }
    singleOf(::KtorRemoteAuthDataSource).bind<RemoteAuthDataSource>()
    singleOf(::DefaultAuthRepository).bind<AuthRepository>()
    single {
        ResetPasswordUseCase(get())
    }
    single{
        LoginUseCase(get(),get())
    }
    single{
        PreferenceManger(get())
    }





}