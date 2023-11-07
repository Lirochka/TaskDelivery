package com.example.taskdelivery.screens.category

import com.example.domain.model.Food
import com.example.domain.model.Meals

data class ListFoodState(
    val isLoading: Boolean = false,
    val allFoodResponse: Food? = null,
    val error: String = ""
)