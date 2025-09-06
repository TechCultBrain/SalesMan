package com.techcult.salesman.feature.RolePermission.di

import com.techcult.salesman.feature.RolePermission.data.RolePermissionRepoImpl
import com.techcult.salesman.feature.RolePermission.domain.RolePermissionRepository
import com.techcult.salesman.feature.RolePermission.presentation.RoleViewModel
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