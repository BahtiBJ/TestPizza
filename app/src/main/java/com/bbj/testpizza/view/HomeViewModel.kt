package com.bbj.testpizza.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbj.testpizza.domain.FetchBannersUseCase
import com.bbj.testpizza.domain.FetchProductsUseCase
import com.bbj.testpizza.domain.models.BannerModel
import com.bbj.testpizza.domain.models.ProductPreview
import com.bbj.testpizza.domain.models.ProductType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val fetchProductsUseCase: FetchProductsUseCase,
    private val fetchBannersUseCase: FetchBannersUseCase
) : ViewModel() {

    private val _productList = MutableLiveData<StateModel<ArrayList<ProductPreview>>>()
    val productList: LiveData<StateModel<ArrayList<ProductPreview>>>
        get() = _productList

    val productsStartIndices = hashMapOf<ProductType, Int>()

    private val _bannersList = MutableLiveData<StateModel<List<BannerModel>>>()
    val bannersList: LiveData<StateModel<List<BannerModel>>>
        get() = _bannersList

    fun requestProductList(isOnline : Boolean) {
        requestData<ArrayList<ProductPreview>>(_productList) {
            val products = fetchProductsUseCase.execute(isOnline)
            calculateStartIndices(products)
            products
        }
    }

    fun requestBannersList(isOnline : Boolean) {
        requestData<List<BannerModel>>(_bannersList) {
            fetchBannersUseCase.execute(isOnline)
        }
    }

    private fun <T> requestData(
        mutableLiveData: MutableLiveData<StateModel<T>>,
        fetchFunction: suspend () -> T
    ) {
        mutableLiveData.value = StateModel.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = fetchFunction()
                mutableLiveData.postValue(StateModel.Success(data))
            } catch (exception: Exception) {
                mutableLiveData.postValue(StateModel.Error(exception))
            }
        }
    }

    private fun calculateStartIndices(products: ArrayList<ProductPreview>) {
        productsStartIndices[ProductType.PIZZA] =
            products.indexOfFirst { it.type == ProductType.PIZZA }
        productsStartIndices[ProductType.COMBO] =
            products.indexOfFirst { it.type == ProductType.COMBO }
        productsStartIndices[ProductType.DESSERT] =
            products.indexOfFirst { it.type == ProductType.DESSERT }
        productsStartIndices[ProductType.DRINK] =
            products.indexOfFirst { it.type == ProductType.DRINK }
    }
}

