package com.bbj.testpizza.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bbj.testpizza.domain.FetchBannersUseCase
import com.bbj.testpizza.domain.FetchProductsUseCase
import com.bbj.testpizza.domain.models.BannerModel
import com.bbj.testpizza.domain.models.ProductPreview

class HomeViewModel(
    private val fetchProductsUseCase: FetchProductsUseCase,
    private val fetchBannersUseCase: FetchBannersUseCase
) : ViewModel() {

    private val _productList = MutableLiveData<ArrayList<ProductPreview>>()
    val productList: LiveData<ArrayList<ProductPreview>>
        get() = _productList

    private val _bannersList = MutableLiveData<ArrayList<BannerModel>>()
    val bannersList: LiveData<ArrayList<BannerModel>>
        get() = _bannersList

    fun requestProductList(){

    }

}