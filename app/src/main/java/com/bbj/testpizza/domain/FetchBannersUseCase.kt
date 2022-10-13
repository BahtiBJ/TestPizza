package com.bbj.testpizza.domain

import com.bbj.testpizza.domain.models.BannerModel

class FetchBannersUseCase(private val menuRepository: MenuRepository) {

    suspend fun execute() : List<BannerModel> {
        return menuRepository.fetchBanners()
    }

}