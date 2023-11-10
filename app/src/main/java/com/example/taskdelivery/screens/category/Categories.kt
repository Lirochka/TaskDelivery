package com.example.taskdelivery.screens.category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskdelivery.screens.category.components.ProductsGrid
import com.example.taskdelivery.screens.category.components.ProfileHeader

@Composable
fun HomeScreen() {
    val myHomeFeedScrollState = rememberLazyListState()
    val toolbarProgress = remember { mutableStateOf(0f) }

    val nestedScrollConnection = object : NestedScrollConnection {

        override fun onPostScroll(
            consumed: Offset,
            available: Offset,
            source: NestedScrollSource,
        ): Offset {

            if (myHomeFeedScrollState.firstVisibleItemIndex == 0) {
                toolbarProgress.value =
                    (myHomeFeedScrollState.firstVisibleItemScrollOffset / 100f).coerceIn(0f, 1f)
            } else {
                toolbarProgress.value = 1f
            }
            return Offset.Zero
        }
    }
    Box() {
        Column(
            Modifier
                .nestedScroll(nestedScrollConnection)
        ) {
            ProductsGrid(
                viewModel = hiltViewModel(),
                lazyListState = myHomeFeedScrollState
            )
        }
        Column {
            ProfileHeader(
                progress = toolbarProgress,
                viewModel = hiltViewModel())
        }
    }
}
