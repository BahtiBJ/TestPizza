package com.bbj.testpizza.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface HomeDatabaseDAO {

    @Query("SELECT * FROM CACHEDBANNERMODEL")
    fun getBanners() : List<CachedBannerModel>

    @Query("SELECT * FROM CachedProductPreview")
    fun getProducts() : List<CachedProductPreview>

    @Insert
    fun insertBanners(cachedBannerModels: List<CachedBannerModel>)

    @Insert
    fun insertProducts(cachedProductPreviews: List<CachedProductPreview>)

    @Query("DELETE FROM CACHEDBANNERMODEL")
    fun clearBannersTable()

    @Query("DELETE FROM CACHEDPRODUCTPREVIEW")
    fun clearProductsTable()

    @Transaction
    fun setBanners(cachedBannerModels: List<CachedBannerModel>){
        clearBannersTable()
        insertBanners(cachedBannerModels)
    }

    @Transaction
    fun setProducts(cachedProductPreviews: List<CachedProductPreview>){
        clearProductsTable()
        insertProducts(cachedProductPreviews)
    }

}