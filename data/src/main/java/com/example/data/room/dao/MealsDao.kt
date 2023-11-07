package com.example.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.room.entity.MealsEntity

@Dao
interface MealsDao {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(meals: List<MealsEntity>)
}