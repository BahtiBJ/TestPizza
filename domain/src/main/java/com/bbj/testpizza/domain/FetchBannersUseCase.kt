package com.bbj.testpizza.domain

import com.bbj.testpizza.domain.models.BannerModel

class FetchBannersUseCase(private val menuRepository: MenuRepository) {

    suspend fun execute(isOnline: Boolean): List<BannerModel> {
        return if (isOnline)
            menuRepository.fetchBanners()
        else
            menuRepository.getCachedBanners()
    }

}