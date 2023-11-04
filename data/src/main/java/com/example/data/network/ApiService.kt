package com.example.data.network

import com.example.data.network.model.FoodDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

        @GET("api/json/v1/1/categories.php")
        suspend fun getFoods(): Response<FoodDto>
}