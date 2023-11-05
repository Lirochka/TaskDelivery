package com.example.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Food

@Entity
data class FoodEntity(
    @PrimaryKey(autoGenerate = false)
    val primaryKeyId: Int = 0,
    val meals: List<MealsEntity>?,
) {
    fun toFood(): Food {
        return Food(
            meals = meals?.map { it.toMovie() }
        )
    }
}