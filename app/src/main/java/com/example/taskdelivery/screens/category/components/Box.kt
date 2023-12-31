package com.example.taskdelivery.screens.category.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId

@Composable
fun MainBox(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .layoutId("box")
    )
}