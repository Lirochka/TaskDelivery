package com.example.data.room

import androidx.room.TypeConverter
import com.example.domain.model.Food
import com.example.domain.model.Meals
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomTypeConvertor {

    @TypeConverter
    fun mealsToString(meals: Meals): String {
        return Gson().toJson(meals)
    }

    @TypeConverter
    fun stringToMeals(str: String): Meals {
        return Gson().fromJson(str, Meals::class.java)
    }
}

class ListOfStringToStringTypeConvertor {

    @TypeConverter
    fun listOfStringToString(str: List<String>): String {
        return Gson().toJson(str)
    }

    @TypeConverter
    fun strToListString(str: String): List<String> {
        return Gson().fromJson(str, object : TypeToken<List<String>>() {}.type)
    }
}