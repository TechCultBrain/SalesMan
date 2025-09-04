package com.techcult.salesman.core.di

import com.techcult.salesman.core.data.networking.HttpClientFactory
import com.techcult.salesman.core.presentation.MainViewModel
import com.techcult.salesman.feature.Home.di.homeModule
import com.techcult.salesman.feature.auth.di.authModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module



expect val platformModule: Module


val coreModule=module{

    includes(authModule,platformModule, homeModule)
    single { HttpClientFactory.create(get()) }
    viewModel{
        MainViewModel(get())
    }


}