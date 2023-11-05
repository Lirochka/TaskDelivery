package com.example.data.mappers

import com.example.common.Mapper
import com.example.data.network.model.FoodDto
import com.example.domain.model.Food
import javax.inject.Inject

class FoodMapper @Inject constructor(
    private val mealsMapper: MealsMapper,
) : Mapper<FoodDto, Food> {

    override fun mapFrom(from: FoodDto) = Food(
        meals = from.meals?.map {
            mealsMapper.mapFrom(it)
        }
    )
}



