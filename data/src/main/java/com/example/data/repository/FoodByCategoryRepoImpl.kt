package com.example.data.repository

import com.example.common.Resource
import com.example.data.network.ApiService
import com.example.data.network.utils.SafeApiRequest
import com.example.data.room.FoodDao
import com.example.domain.model.Meals
import com.example.domain.repository.FoodByCategoryRepo
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class FoodByCategoryRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val foodDao: FoodDao
): FoodByCategoryRepo, SafeApiRequest() {
    override fun getFoodsByCategory(category: String): Flow<Resource<List<Meals>>> = flow {
        emit(Resource.Loading())
        try {
            fetchAndInsertPopularMovies(apiService, foodDao, category)
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
        emit(Resource.Success(getMealsFromDb(foodDao)))
    }


    private suspend fun fetchAndInsertPopularMovies(
        foodApiService: ApiService,
        foodDao: FoodDao,
        category: String
    ) {

        val remoteMeals = foodApiService.getFoodsByCategory(category)
        remoteMeals.meals?.let { foodDao.upsert(it.map { it.toMealsEntity() }) }

    }

    private suspend fun getMealsFromDb(foodDao: FoodDao): List<Meals> {
        val newMeals = foodDao.getAllFood().map { it.toMovie()}
        return newMeals
    }
}