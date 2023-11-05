package com.example.taskdelivery.screens.category

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.domain.model.Food
import com.example.domain.model.Meals
import com.example.taskdelivery.R
import com.example.taskdelivery.screens.category.components.CategoryTab
import com.example.taskdelivery.screens.category.components.FoodCard
import com.example.taskdelivery.screens.category.components.MainTopAppBar

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Categories(
    viewModel: CategoryViewModel = hiltViewModel(),
) {
    val listFoodByCategoryState = viewModel.listFoodByCategoryState.value
    val selectedCategory = viewModel.selectedCategory.value
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column() {
            MainTopAppBar()
            Spacer(modifier = Modifier.height(8.dp))
            CategoryTab(
                categoryList = getAllFoodCategory(),
                isSelected = selectedCategory?.categoryName,
                onFetchCategory = {
                    viewModel.onSelectedCategoryChanged(it)
                    viewModel.getListByCategory(it)
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (listFoodByCategoryState.allFoodResponse != null) {
                listFoodByCategoryState.allFoodResponse?.let {
                    FoodCard(
                        meals = it
                    )
                }
            }
        }
        if (listFoodByCategoryState.error.isNotBlank()) {
            Toast.makeText(context, listFoodByCategoryState.error, Toast.LENGTH_SHORT).show()
        }
        if (listFoodByCategoryState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}




