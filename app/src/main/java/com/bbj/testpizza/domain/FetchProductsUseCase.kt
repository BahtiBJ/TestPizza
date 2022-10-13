package com.bbj.testpizza.domain

import com.bbj.testpizza.domain.models.ProductPreview

class FetchProductsUseCase(private val menuRepository: MenuRepository) {

    suspend fun execute(): ArrayList<ProductPreview> {
        val productList = menuRepository.fetchMenu()
        productList.sortBy { it.type }
        return productList
    }

}