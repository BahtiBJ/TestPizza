package com.bbj.testpizza.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CachedProductPreview(
    val name: String,
    val posterPath: String,
    val describtion: String,
    val price: Int,
    val type : String,
    @PrimaryKey(autoGenerate = true) val id : Int = 0)


