package com.techcult.salesman.feature.Settings.store.di


import com.techcult.salesman.core.data.database.AppDatabase
import com.techcult.salesman.feature.Settings.store.data.database.StoreInfoDao
import com.techcult.salesman.feature.Settings.store.data.repository.DefaultStoreInfoRepo
import com.techcult.salesman.feature.Settings.store.domain.StoreRepository
import com.techcult.salesman.feature.Settings.store.presentation.StoreInfoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val storeModule= module {
    single {
        get<AppDatabase>().storeInfoDao()
    }

    single {
        DefaultStoreInfoRepo(get())
    }.bind<StoreRepository>()
    viewModel {
        StoreInfoViewModel(
            get()
        )

    }
}