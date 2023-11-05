package com.example.domain.repository

import com.example.common.Resource
import com.example.domain.model.Food
import com.example.domain.model.Meals
import kotlinx.coroutines.flow.Flow

interface FoodByCategoryRepo {

    fun getFoodsByCategory(category: String): Flow<Resource<List<Meals>>>
}