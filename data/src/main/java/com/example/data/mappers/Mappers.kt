package com.example.data.mappers

import com.example.data.network.model.FoodDto
import com.example.domain.model.Food

fun List<FoodDto>.toDomain(): List<Food> {

    return map{
        Food(
            id = it.id ?: "",
            category = it.category ?: "",
            description = it.description ?: "",
            image = it.image ?: ""
        )
    }
}
