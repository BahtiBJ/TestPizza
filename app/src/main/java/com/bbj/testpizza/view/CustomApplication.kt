package com.bbj.testpizza.view

import android.app.Application
import com.bbj.testpizza.di.appModule
import com.bbj.testpizza.di.dataModule
import com.bbj.testpizza.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@CustomApplication)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }

}