package com.example.data.repository

import android.util.Log
import com.example.common.Resource
import com.example.data.network.ApiService
import com.example.data.network.utils.SafeApiRequest
import com.example.data.room.dao.FoodDao
import com.example.data.room.dao.MealsDao
import com.example.data.room.entity.FoodEntity
import com.example.domain.model.Food
import com.example.domain.model.Meals
import com.example.domain.repository.FoodByCategoryRepo
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class FoodByCategoryRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val foodDao: FoodDao,
    private val mealsDao: MealsDao
): FoodByCategoryRepo, SafeApiRequest() {

    private val foodEntity: FoodEntity = FoodEntity(0, listOf())
    override fun getFoodsByCategory(category: String): Flow<Resource<Food>> = flow {
        emit(Resource.Loading())
        try {
          fetchAndInsertFood(apiService,foodDao, mealsDao, category)
            Log.d("KKK", "FoodByCategoryRepoImpl: ${apiService.getFoodsByCategory(category)}")
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection."
                )
            )
        }
        emit(Resource.Success(getFoodFromDb(foodDao)))
    }


    private suspend fun fetchAndInsertFood(
        foodApiService: ApiService,
        foodDao: FoodDao,
        mealsDao: MealsDao,
        category: String
    ) {
        val remoteFood = foodApiService.getFoodsByCategory(category)
        foodDao.insertFood(remoteFood.toFoodEntity())
        remoteFood.meals?.let { mealsDao.upsert(it.map { it.toMealsEntity() }) }
    }

    private suspend fun getFoodFromDb(foodDao: FoodDao): Food {
        val newFood = foodDao.getAllFood().let { it.toFood()}
        return newFood
    }
}