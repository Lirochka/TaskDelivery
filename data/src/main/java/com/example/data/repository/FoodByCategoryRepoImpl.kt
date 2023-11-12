package com.example.data.repository

import com.example.common.Resource
import com.example.data.network.ApiService
import com.example.data.room.dao.FoodDao
import com.example.data.room.dao.MealsDao
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
): FoodByCategoryRepo {
    override fun getFoodsByCategory(category: String): Flow<Resource<List<Meals>>> = flow {
        emit(Resource.Loading())
        try {
          fetchAndInsertFood(apiService,foodDao, mealsDao, category)
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
        emit(Resource.Success(getFoodFromDb(mealsDao, category)))
    }


    private suspend fun fetchAndInsertFood(
        foodApiService: ApiService,
        foodDao: FoodDao,
        mealsDao: MealsDao,
        category: String
    ) {
        val remoteFood = foodApiService.getFoodsByCategory(category)
        foodDao.insertFood(remoteFood.toFoodEntity(category))
        remoteFood.meals?.let { mealsDao.upsert(it.map { it.toMealsEntity(category) }) }
    }

    private suspend fun getFoodFromDb(mealsDao: MealsDao, category: String): List<Meals> {
        val newFood = mealsDao.getAllFood(category).map { it.toMeals()}
        return newFood
    }
}