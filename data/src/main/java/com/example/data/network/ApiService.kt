package com.example.data.network

import com.example.data.network.model.FoodDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/json/v1/1/filter.php")
    suspend fun getFoodsByCategory(
        @Query("c") category: String,
    ): FoodDto
}