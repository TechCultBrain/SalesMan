package com.techcult.salesman.feature.tax.di

import com.techcult.salesman.core.data.database.AppDatabase
import com.techcult.salesman.feature.tax.data.TaxRepositoryImpl
import com.techcult.salesman.feature.tax.domain.TaxRepository
import com.techcult.salesman.feature.tax.presentation.TaxViewModel
import com.techcult.salesman.feature.user.data.UserRepositoryImpl
import com.techcult.salesman.feature.user.domain.GetUserListUseCase
import com.techcult.salesman.feature.user.domain.UserRepository
import com.techcult.salesman.feature.user.presentation.UserViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val taxModule= module {

    single {
        get<AppDatabase>().taxDao()
    }
    singleOf(::TaxRepositoryImpl).bind<TaxRepository>()

    viewModel { TaxViewModel(get()) }


}