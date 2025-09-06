package com.techcult.salesman.core.di

import android.content.Context
import com.techcult.salesman.core.data.database.AppDatabase
import com.techcult.salesman.core.data.database.DatabaseFactory
import com.techcult.salesman.core.data.database.UserDao
import com.techcult.salesman.core.data.prefernces.createDataStore
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module =
    module {

        single<HttpClientEngine> { OkHttp.create() }

        single {
            createDataStore(context = get<Context>())
        }
        single {
            DatabaseFactory(context = get())
        }



    }