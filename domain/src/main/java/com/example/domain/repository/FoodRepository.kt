package com.example.domain.repository

import com.example.domain.model.Food
import retrofit2.Response

interface FoodRepository {

    suspend fun getFoods(): Response<List<Food>>
}