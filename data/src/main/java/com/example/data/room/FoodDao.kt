package com.example.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.room.entity.MealsEntity
import com.example.domain.model.Meals
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Query("SELECT * FROM mealsEntity")
    suspend fun getAllFood(): List<MealsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(meals: List<MealsEntity>)
}