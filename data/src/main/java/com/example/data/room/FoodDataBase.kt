package com.example.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.room.entity.FoodEntity
import com.example.data.room.entity.MealsEntity
import com.example.domain.model.Food
import com.example.domain.model.Meals

@Database(
    entities = [MealsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomTypeConvertor::class, ListOfStringToStringTypeConvertor::class)
abstract class FoodDataBase : RoomDatabase() {

    abstract fun getSavedFoodDao(): FoodDao

    companion object {
        @Volatile
        private var INSTANCE: FoodDataBase? = null

        fun getDatabase(context: Context): FoodDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodDataBase::class.java,
                    "food_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}