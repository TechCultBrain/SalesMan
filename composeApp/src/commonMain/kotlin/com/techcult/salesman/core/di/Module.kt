package com.techcult.salesman.core.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.techcult.salesman.core.data.database.AppDatabase
import com.techcult.salesman.core.data.database.DatabaseFactory
import com.techcult.salesman.core.data.networking.HttpClientFactory
import com.techcult.salesman.core.presentation.MainViewModel
import com.techcult.salesman.feature.Home.di.homeModule
import com.techcult.salesman.feature.RolePermission.di.roleModule
import com.techcult.salesman.feature.Settings.di.settingsModule
import com.techcult.salesman.feature.auth.di.authModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


expect val platformModule: Module


val coreModule = module {

    includes(authModule, platformModule, homeModule,settingsModule, roleModule)
    single { HttpClientFactory.create(get()) }
    single {
        get<DatabaseFactory>().create()

            .setDriver(BundledSQLiteDriver())
            .build()

    }
    single {
        get<AppDatabase>().userDao()
    }
    viewModel {
        MainViewModel(get())
    }


}