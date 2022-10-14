package com.bbj.testpizza.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CachedBannerModel::class, CachedProductPreview::class], version = 1)
abstract class HomeDatabase : RoomDatabase() {

    abstract fun getDao() : HomeDatabaseDAO

}