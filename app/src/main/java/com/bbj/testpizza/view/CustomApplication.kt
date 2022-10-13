package com.bbj.testpizza.view

import android.app.Application
import com.bbj.testpizza.di.appModule
import com.bbj.testpizza.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@CustomApplication)
            androidLogger(Level.DEBUG)
            modules(listOf(appModule, domainModule))
        }
    }

}