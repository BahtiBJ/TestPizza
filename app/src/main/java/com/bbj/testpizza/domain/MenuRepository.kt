package com.bbj.testpizza.domain

import com.bbj.testpizza.domain.models.BannerModel
import com.bbj.testpizza.domain.models.ProductPreview

interface MenuRepository {

    suspend fun fetchMenu() : ArrayList<ProductPreview>

    suspend fun fetchBanners() : List<BannerModel>

//    suspend fun getCachedMenu() : ArrayList<ProductPreview>
//
//    suspend fun getCachedBanners() : List<BannerModel>

}