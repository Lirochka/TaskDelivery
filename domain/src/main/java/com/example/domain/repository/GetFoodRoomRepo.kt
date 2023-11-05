package com.example.domain.repository


import com.example.domain.model.Meals
import kotlinx.coroutines.flow.Flow

interface GetFoodRoomRepo{

   fun getSavedFood(): Flow<List<Meals>>

    suspend fun upsert(meals: Meals): Long
}