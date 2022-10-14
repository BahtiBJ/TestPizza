package com.bbj.testpizza.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CachedBannerModel(val imagePath : String,@PrimaryKey(autoGenerate = true) val id : Int = 0)