package com.example.data.network.model

import com.example.data.room.entity.FoodEntity
import com.google.gson.annotations.SerializedName

data class FoodDto(

    @SerializedName("meals") val meals: List<MealsDto>,
) {
    fun toFoodEntity(category: String): FoodEntity {
        return FoodEntity(
            meals = meals.map { it.toMealsEntity(category) }
        )
    }
}