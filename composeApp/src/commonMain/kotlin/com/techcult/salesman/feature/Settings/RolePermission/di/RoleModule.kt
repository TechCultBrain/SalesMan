package com.techcult.salesman.feature.Settings.RolePermission.di

import com.techcult.salesman.feature.Settings.RolePermission.data.RolePermissionRepoImpl
import com.techcult.salesman.feature.Settings.RolePermission.domain.RolePermissionRepository
import com.techcult.salesman.feature.Settings.RolePermission.presentation.RoleViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val roleModule = module {

    singleOf(::RolePermissionRepoImpl).bind<RolePermissionRepository>()
    viewModel {
        RoleViewModel(get())
    }
}