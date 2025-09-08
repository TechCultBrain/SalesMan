package com.techcult.salesman.feature.user.di

import com.techcult.salesman.feature.user.data.UserRepositoryImpl
import com.techcult.salesman.feature.user.domain.GetUserListUseCase
import com.techcult.salesman.feature.user.domain.UserRepository
import com.techcult.salesman.feature.user.presentation.UserViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val userModule= module {
    singleOf(::UserRepositoryImpl).bind<UserRepository>()

    viewModel { UserViewModel(get(), get()) }
    single {
        GetUserListUseCase(get())
    }
    viewModel {
        UserViewModel(get(),get())
    }

}