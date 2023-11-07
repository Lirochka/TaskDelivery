package com.example.taskdelivery.screens.category.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.domain.model.Food
import com.example.domain.model.Meals
import com.example.taskdelivery.R

@Composable
fun FoodCard(
    meals: List<Meals>,
    modifier: Modifier = Modifier,
   lazyListState: LazyListState,
) {

    LazyColumn(
        contentPadding = PaddingValues(top = 260.dp, bottom = 16.dp),
        state = lazyListState
    ) {
        items(meals) { meals ->
            Card(
                modifier
                    .fillMaxWidth()
                    .padding(8.dp)
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
                        Log.d("KKK", "Text: ${meals.title}")
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