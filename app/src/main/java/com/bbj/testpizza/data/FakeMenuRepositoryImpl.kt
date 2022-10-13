package com.bbj.testpizza.data

import android.content.res.Resources
import android.net.Uri
import com.bbj.testpizza.R
import com.bbj.testpizza.domain.models.BannerModel
import com.bbj.testpizza.domain.MenuRepository
import com.bbj.testpizza.domain.models.ProductPreview
import com.bbj.testpizza.domain.models.ProductType


class FakeMenuRepositoryImpl() : MenuRepository {

    override suspend fun fetchMenu(): ArrayList<ProductPreview> {
        val productPizza = ProductPreview(
            "Ветчина и грибы",
            getPathForResource("pizza_1.png"),
            "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус",
            385,
            ProductType.PIZZA
        )

        val productCombo = ProductPreview(
            "Комбо 2 пиццы с циплёнком",
            getPathForResource("combo.jpg"),
            "Идеальный набор для двоих!",
            920,
            ProductType.COMBO
        )

        val productDessert = ProductPreview(
            "Джелато",
            getPathForResource("djelato.png"),
            "Итальянский замороженный десерт из свежего коровьего молока и сахара",
            490,
            ProductType.DESSERT
        )

        val productDrink = ProductPreview(
            "Байкал",
            getPathForResource("baical.png"),
            "Сильногазированный тонизирующий напиток, разработанный в СССР как местный аналог «Кока-колы» и «Пепси-колы»",
            50,
            ProductType.DRINK
        )
        val productList = arrayListOf<ProductPreview>().apply {
            repeat(5){
                add(productPizza)
                add(productCombo)
                add(productDessert)
                add(productDrink)
            }
        }
        return productList
    }

    override suspend fun fetchBanners(): List<BannerModel> {
        val bannerModel1 = BannerModel(getPathForResource("banner_1.jpg"))
        val bannerModel2 = BannerModel(getPathForResource("banner_2.jpg"))
        val bannerList = listOf(bannerModel1,bannerModel2,bannerModel1,bannerModel2)
        return bannerList
    }


    private fun getPathForResource(fileName : String): String {
        return "android.resource://app/src/main/res/drawable/$fileName"
    }
}