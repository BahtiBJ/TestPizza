package com.bbj.testpizza.domain

import com.bbj.testpizza.domain.models.BannerModel
import com.bbj.testpizza.domain.models.ProductPreview

interface DataStorage {

    suspend fun getCachedMenu() : ArrayList<ProductPreview>

    suspend fun getCachedBanners() : List<BannerModel>

}