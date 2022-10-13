package com.bbj.testpizza.domain

interface MenuRepository {

    fun fetchMenu() : ArrayList<ProductPreview>

    fun fetchBanners() : List<BannerModel>

}