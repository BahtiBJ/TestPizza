package com.bbj.testpizza.di

import androidx.room.Room
import com.bbj.testpizza.data.room.HomeDatabase
import org.koin.dsl.module

val dataModule = module {

    single {
        fun provideDataBase(): HomeDatabase {
            return Room.databaseBuilder(get(), HomeDatabase::class.java, "home_database").build()
        }

        provideDataBase().getDao()
    }

}