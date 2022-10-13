package com.bbj.testpizza.domain

data class ProductPreview(
    val name: String,
    val posterPath: String,
    val describtion: String,
    val price: Int,
    val type : ProductType
)