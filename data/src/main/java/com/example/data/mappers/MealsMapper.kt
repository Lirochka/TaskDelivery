package com.example.data.mappers

import com.example.common.Mapper
import com.example.data.network.model.MealsDto
import com.example.domain.model.Meals
import javax.inject.Inject


class MealsMapper @Inject constructor() : Mapper<MealsDto, Meals> {

    override fun mapFrom(from: MealsDto) = Meals(
        title = from.title ?: "",
        imageUrl = from.imageUrl ?: "",
        id = from.id ?: ""
    )
}


