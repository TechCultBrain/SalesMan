package com.techcult.salesman

import android.app.Application
import com.techcult.salesman.core.di.initKoin
import org.koin.android.ext.koin.androidContext

class SalesApp: Application() {

    override fun onCreate() {
        super.onCreate()
      initKoin()
      {
          androidContext(this@SalesApp)
      }
    }
}
