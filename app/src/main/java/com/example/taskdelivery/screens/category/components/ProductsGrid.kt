package com.example.taskdelivery.screens.category.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.taskdelivery.screens.category.CategoryViewModel

@Composable
fun ProductsGrid(
    viewModel: CategoryViewModel,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState,
) {
    val listFoodByCategoryState = viewModel.listFoodByCategoryState.value
    val context = LocalContext.current

    if (listFoodByCategoryState.allFoodResponse != null) {
        listFoodByCategoryState.allFoodResponse.let {
                FoodCard(
                    meals = it,
                    modifier = modifier,
                    lazyListState = lazyListState
                )
        }
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        if (listFoodByCategoryState.error.isNotBlank()) {
            Toast.makeText(context, listFoodByCategoryState.error, Toast.LENGTH_SHORT).show()
        }
        if (listFoodByCategoryState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}