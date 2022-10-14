package com.bbj.testpizza.domain

import com.bbj.testpizza.domain.models.BannerModel
import com.bbj.testpizza.domain.models.ProductPreview

interface MenuRepository : DataStorage {

    suspend fun fetchMenu() : ArrayList<ProductPreview>

    suspend fun fetchBanners() : List<BannerModel>
}