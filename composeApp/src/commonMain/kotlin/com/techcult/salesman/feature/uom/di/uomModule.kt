package com.techcult.salesman.feature.uom.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.techcult.salesman.core.data.database.AppDatabase
import com.techcult.salesman.feature.uom.data.UomRepositoryImpl
import com.techcult.salesman.feature.uom.domain.UomRepository
import com.techcult.salesman.feature.uom.presentation.UomViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val uomModule = module {

    single {
        get<AppDatabase>().uomDao()
    }
    single {
        UomRepositoryImpl(get())
    }
        .bind<UomRepository>()
    viewModel {
        UomViewModel(get())
    }
}