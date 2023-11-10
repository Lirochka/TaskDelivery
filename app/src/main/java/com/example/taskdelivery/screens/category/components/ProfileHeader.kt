package com.example.taskdelivery.screens.category.components

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.example.taskdelivery.R
import com.example.taskdelivery.screens.category.CategoryViewModel
import com.example.taskdelivery.screens.category.getAllFoodCategory

@OptIn(ExperimentalMotionApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProfileHeader(
    viewModel: CategoryViewModel,
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





