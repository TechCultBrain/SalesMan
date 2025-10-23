package com.techcult.salesman.feature.discount.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.techcult.salesman.core.data.database.AppDatabase
import com.techcult.salesman.feature.discount.data.DiscountRepositoryImpl
import com.techcult.salesman.feature.discount.domain.DiscountRepository
import com.techcult.salesman.feature.discount.presentation.DiscountViewModel
import com.techcult.salesman.feature.product.data.repository.UomRepositoryImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val discountModule= module {

    single {
        get<AppDatabase>().discountDao()
    }
    single {
        DiscountRepositoryImpl(get())
    }.bind<DiscountRepository>()

    viewModel {
        DiscountViewModel(get())

    }





}