package com.bbj.testpizza.data

import com.bbj.testpizza.R
import com.bbj.testpizza.domain.MenuRepository
import com.bbj.testpizza.domain.models.BannerModel
import com.bbj.testpizza.domain.models.ProductPreview
import com.bbj.testpizza.domain.models.ProductType


class FakeMenuRepositoryImpl() : MenuRepository {

    override suspend fun fetchMenu(): ArrayList<ProductPreview> {
        val productPizza = ProductPreview(
            "Ветчина и грибы",
            getPathForResource(R.drawable.pizza_1),
            "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус",
            385,
            ProductType.PIZZA
        )

        val productCombo = ProductPreview(
            "Комбо 2 пиццы с циплёнком",
            getPathForResource(R.drawable.combo),
            "Идеальный набор для двоих!",
            920,
            ProductType.COMBO
        )

        val productDessert = ProductPreview(
            "Джелато",
            getPathForResource(R.drawable.djelato),
            "Итальянский замороженный десерт из свежего коровьего молока и сахара",
            490,
            ProductType.DESSERT
        )

        val productDrink = ProductPreview(
            "Байкал",
            getPathForResource(R.drawable.baical),
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
        val bannerModel1 = BannerModel(getPathForResource(R.drawable.banner_1))
        val bannerModel2 = BannerModel(getPathForResource(R.drawable.banner_2))
        val bannerList = listOf(bannerModel1,bannerModel2,bannerModel1,bannerModel2)
        return bannerList
    }


    private fun getPathForResource(resourceId : Int): String {
        return "android.resource://" + R::class.java.getPackage().getName() + "/" + resourceId
    }
}