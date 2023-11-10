package com.example.data.network.model


import com.example.data.room.entity.MealsEntity
import com.google.gson.annotations.SerializedName


data class MealsDto(

    @SerializedName("strMeal") var title: String?,
    @SerializedName("strMealThumb") var imageUrl: String?,
    @SerializedName("idMeal") var id: String?,
) {

    fun toMealsEntity(category: String): MealsEntity {
        return MealsEntity(
            title = title ?: "",
            imageUrl = imageUrl ?: "",
            id = id ?: "",
            category = category
        )
    }
}