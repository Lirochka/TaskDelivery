package com.example.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FoodKey(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var prev: Int?,
    var next: Int?
)
