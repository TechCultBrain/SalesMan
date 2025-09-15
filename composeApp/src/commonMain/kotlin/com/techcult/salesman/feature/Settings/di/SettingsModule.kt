package com.techcult.salesman.feature.Settings.di

import com.techcult.salesman.feature.Settings.RolePermission.di.roleModule
import com.techcult.salesman.feature.Settings.store.di.storeModule
import com.techcult.salesman.feature.user.data.UserRepositoryImpl
import com.techcult.salesman.feature.user.domain.GetUserListUseCase
import com.techcult.salesman.feature.user.domain.UserRepository
import com.techcult.salesman.feature.user.presentation.UserViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val settingsModule = module {
    includes(storeModule, roleModule)

}