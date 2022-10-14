package com.bbj.testpizza.di

import com.bbj.testpizza.data.FakeMenuRepositoryImpl
import com.bbj.testpizza.domain.FetchBannersUseCase
import com.bbj.testpizza.domain.FetchProductsUseCase
import com.bbj.testpizza.domain.MenuRepository
import org.koin.dsl.module

val domainModule = module {

    factory<MenuRepository> {
        FakeMenuRepositoryImpl(get())
    }

    factory {
        FetchBannersUseCase(get())
    }

    factory {
        FetchProductsUseCase(get())
    }
}