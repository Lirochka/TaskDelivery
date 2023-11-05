package com.example.taskdelivery.screens.category.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskdelivery.R
import com.example.taskdelivery.screens.category.FoodCategory

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
                Box(
                    modifier = Modifier.background(
                        color =
                        if (it.categoryName.contains(isSelected.toString()))
                            colorResource(id = R.color.purple_300)
                        else Color.White
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
                            color = if (it.categoryName.contains(isSelected.toString()))
                                colorResource(id = R.color.purple_400)
                            else Color.Gray,
                            fontFamily = FontFamily.Serif
                        )
                    )
                }
            }
        }
    }
}