package com.example.taskdelivery.screens.category

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskdelivery.R
import com.example.taskdelivery.screens.category.components.Banner
import com.example.taskdelivery.screens.category.components.CategoryTab
import com.example.taskdelivery.screens.category.components.FoodCard
import com.example.taskdelivery.screens.category.components.MainBox
import com.example.taskdelivery.screens.category.components.Searchbar

@OptIn(ExperimentalMotionApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProfileHeader(
    viewModel: CategoryViewModel = hiltViewModel(),
    progress: MutableState<Float>,
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val isSearchbarFoucused = remember { mutableStateOf(false) }


    val selectedCategory = viewModel.selectedCategory.value

    val motionString = remember {
        context.resources.openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }

    BackHandler(enabled = isSearchbarFoucused.value) {
        focusManager.clearFocus()
    }

    MotionLayout(
        motionScene = MotionScene(content = motionString),
        progress = progress.value,
        modifier = Modifier.fillMaxWidth()
    ) {
        MainBox()
        Searchbar()
        Banner()
        CategoryTab(
            categoryList = getAllFoodCategory(),
            isSelected = selectedCategory?.categoryName,
            onFetchCategory = {
                viewModel.onSelectedCategoryChanged(it)
                viewModel.getListByCategory(it)
            }
        )
    }
}





