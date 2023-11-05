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


@Composable
fun CategoryTab(
    categoryList: List<FoodCategory>,
    isSelected: String?,
    onFetchCategory: (String) -> Unit,
) {
    LazyRow(modifier = Modifier.padding(start = 15.dp)) {
        items(categoryList) {
            Card(
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable { onFetchCategory(it.categoryName) }
            ) {
                Log.d("TAG", "CategoryItem: " + isSelected)
                Box(
                    modifier = Modifier.background(
                        color =
                        if (it.categoryName.contains(isSelected.toString()))
                            colorResource(id = R.color.purple_200)
                        else Color.Gray
                    )
                ) {
                    Text(
                        it.categoryName,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(
                            start = 12.dp,
                            top = 5.dp,
                            bottom = 5.dp,
                            end = 12.dp
                        ),
                        style = TextStyle(
                            fontSize = 15.sp,
                            color = Color.White,
                            fontFamily = FontFamily.Serif
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun FoodCard(
    meals: List<Meals>,
    modifier: Modifier = Modifier,
) {
    LazyColumn {
        items(meals) { meals ->
            Card(
                modifier.padding(8.dp),
                border = BorderStroke(2.dp, colorResource(id = R.color.purple_500))
            ) {
                Row(
                    modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    AsyncImage(
                        model = meals.imageUrl,
                        modifier = Modifier.size(100.dp),
                        error = painterResource(id = R.drawable.ic_launcher_background),
                        placeholder = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Column {
                        Text(
                            text = meals.title ?: "Not Available",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(4.dp))
                        Row {
                            Box(Modifier.weight(1f))
                            OutlinedButton(
                                border = BorderStroke(1.dp, Color.Red),
                                shape = RoundedCornerShape(5.dp),
                                onClick = { /*TODO*/ },
                            ) {
                                Text(
                                    color = Color.Red,
                                    text = "от 345 р"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}