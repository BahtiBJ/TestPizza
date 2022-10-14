package com.bbj.testpizza.domain

import com.bbj.testpizza.domain.models.ProductPreview

class FetchProductsUseCase(private val menuRepository: MenuRepository) {

    suspend fun execute(isOnline: Boolean): ArrayList<ProductPreview> {
        val productList = if (isOnline)
            menuRepository.fetchMenu()
        else
            menuRepository.getCachedMenu()
        productList.sortBy { it.type }
        return productList
    }

}