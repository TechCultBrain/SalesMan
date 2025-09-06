package com.techcult.salesman.feature.Home.di

import com.techcult.salesman.feature.Home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule= module {
    viewModel { HomeViewModel(get()) }

}