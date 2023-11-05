package com.example.taskdelivery.screens.category

import com.example.taskdelivery.screens.category.FoodCategory.BEEF
import com.example.taskdelivery.screens.category.FoodCategory.CHICKEN
import com.example.taskdelivery.screens.category.FoodCategory.DESSERT
import com.example.taskdelivery.screens.category.FoodCategory.PASTA
import com.example.taskdelivery.screens.category.FoodCategory.PORK
import com.example.taskdelivery.screens.category.FoodCategory.SEAFOOD
import com.example.taskdelivery.screens.category.FoodCategory.VEGAN


enum class FoodCategory(val categoryName: String) {
    BEEF("beef"),
    CHICKEN("chicken"),
    DESSERT("dessert"),
    PASTA("pasta"),
    PORK("pork"),
    SEAFOOD("seafood"),
    VEGAN("vegan")
}

fun getAllFoodCategory(): List<FoodCategory> {
    return listOf(BEEF, CHICKEN, DESSERT, PASTA, PORK, SEAFOOD, VEGAN)
}

fun getFoodCategory(category: String?): FoodCategory?{
    val map = FoodCategory.values().associateBy(FoodCategory::categoryName)
    return map[category]
}