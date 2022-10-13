package com.bbj.testpizza.di


import com.bbj.testpizza.view.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        HomeViewModel(
            fetchBannersUseCase = get(),
            fetchProductsUseCase = get()
        )
    }
}