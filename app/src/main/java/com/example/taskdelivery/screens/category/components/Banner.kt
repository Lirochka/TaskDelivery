package com.example.taskdelivery.screens.category.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.taskdelivery.R

@Composable
fun Banner(){

    LazyRow(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .layoutId("banner")
    ) {
        items(10) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)),
                painter = painterResource(id = R.drawable.rectangle),
                contentDescription = null
            )
        }
    }
}