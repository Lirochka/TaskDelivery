package com.example.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    val primaryKeyId: Int? = null,
    val meals: List<MealsEntity>,
)