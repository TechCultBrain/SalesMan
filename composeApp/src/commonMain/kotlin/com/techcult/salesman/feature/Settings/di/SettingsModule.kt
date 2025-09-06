package com.techcult.salesman.feature.Settings.di

import com.techcult.salesman.feature.user.data.UserRepositoryImpl
import com.techcult.salesman.feature.user.domain.GetUserListUseCase
import com.techcult.salesman.feature.user.domain.UserRepository
import com.techcult.salesman.feature.user.presentation.UserViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val settingsModule = module {
    singleOf(::UserRepositoryImpl).bind<UserRepository>()
    single {
        GetUserListUseCase(get())
    }
    viewModel {
        UserViewModel(get())
    }

}