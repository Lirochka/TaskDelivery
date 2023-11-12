package com.example.taskdelivery.screens.category

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Resource
import com.example.domain.use_cases.GetFoodsCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getFoodsCategoryUseCase: GetFoodsCategoryUseCase,
) : ViewModel() {

    private val _listFoodByCategoryState = mutableStateOf(ListFoodState())
    val listFoodByCategoryState: State<ListFoodState> = _listFoodByCategoryState

    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)

    init {
        getListByCategory("beef")
        onSelectedCategoryChanged("beef")
    }
    fun getListByCategory(
        category: String,
    ) {
        getFoodsCategoryUseCase.invoke(category).onEach { response ->
            Log.d("GGG", "getFoodsCategoryUseCase: ${getFoodsCategoryUseCase.invoke(category)}")

            when (response) {
                is Resource.Loading -> {
                    _listFoodByCategoryState.value = ListFoodState(isLoading = true)
                }
                is Resource.Success -> {
                    _listFoodByCategoryState.value = ListFoodState(
                        allFoodResponse = response.data,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _listFoodByCategoryState.value =
                        ListFoodState(error = response.message ?: "unexpected error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onSelectedCategoryChanged(category: String?) {
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
    }
}