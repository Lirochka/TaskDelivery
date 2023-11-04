package com.example.data.network.model

import com.google.gson.annotations.SerializedName

data class FoodDto(
    @SerializedName("idCategory") val id: String?,
    @SerializedName("strCategory")val category: String?,
    @SerializedName("strCategoryDescription")val description: String?,
    @SerializedName("strCategoryThumb")val image: String?
)