package com.example.data.room.converter

import androidx.room.TypeConverter
import com.example.data.room.entity.FoodEntity
import com.example.data.room.entity.MealsEntity
import com.example.domain.model.Food
import com.example.domain.model.Meals
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class FoodEntityConverter {

    @TypeConverter
    fun fromStringToFoodList(value: String): Food? =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            .adapter<Food>(Types.newParameterizedType(List::class.java, Food::class.java))
            .fromJson(value)

    @TypeConverter
    fun fromFoodListTypeToString(movieListType: Food?): String =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            .adapter<Food>(Types.newParameterizedType(List::class.java, Meals::class.java))
            .toJson(movieListType)


    @TypeConverter
    fun fromStringToFoodEntityList(value: String): FoodEntity? =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build().adapter<FoodEntity>(
                Types.newParameterizedType(
                    List::class.java,
                    MealsEntity::class.java
                )
            ).fromJson(value)

    @TypeConverter
    fun fromFoodEntityListTypeToString(mealsEntityListType: FoodEntity?): String =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build().adapter<FoodEntity>(
                Types.newParameterizedType(
                    List::class.java,
                    MealsEntity::class.java
                )
            ).toJson(mealsEntityListType)
}