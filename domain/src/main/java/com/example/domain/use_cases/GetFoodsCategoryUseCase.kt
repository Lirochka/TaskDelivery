package com.example.domain.use_cases

import com.example.common.Resource
import com.example.domain.model.Food
import com.example.domain.model.Meals
import com.example.domain.repository.FoodByCategoryRepo
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class GetFoodsCategoryUseCase @Inject constructor(
    private val repository: FoodByCategoryRepo,
) {
    operator fun invoke(
        category: String,
    ): Flow<Resource<List<Meals>>> {

        return repository.getFoodsByCategory(category)
    }
}