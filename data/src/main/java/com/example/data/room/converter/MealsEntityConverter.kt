package com.example.data.room.converter

import androidx.room.TypeConverter
import com.example.data.room.entity.MealsEntity
import com.example.domain.model.Meals
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MealsEntityConverter {

    @TypeConverter
    fun fromStringToMealsList(value: String): List<Meals>? =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            .adapter<List<Meals>>(Types.newParameterizedType(List::class.java, Meals::class.java))
            .fromJson(value)

    @TypeConverter
    fun fromMealsListTypeToString(movieListType: List<Meals>?): String =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
            .adapter<List<Meals>>(Types.newParameterizedType(List::class.java, Meals::class.java))
            .toJson(movieListType)


    @TypeConverter
    fun fromStringToMealsEntityList(value: String): List<MealsEntity>? =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build().adapter<List<MealsEntity>>(
                Types.newParameterizedType(
                    List::class.java,
                    MealsEntity::class.java
                )
            ).fromJson(value)

    @TypeConverter
    fun fromMealsEntityListTypeToString(mealsEntityListType: List<MealsEntity>?): String =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build().adapter<List<MealsEntity>>(
                Types.newParameterizedType(
                    List::class.java,
                    MealsEntity::class.java
                )
            ).toJson(mealsEntityListType)
}