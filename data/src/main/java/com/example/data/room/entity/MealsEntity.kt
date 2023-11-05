package com.example.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Meals


@Entity
data class MealsEntity(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var title: String?,
    var imageUrl: String?,
) {

    fun toMovie(): Meals {
        return Meals(
            id = id ?: "",
            title = title ?: "",
            imageUrl = imageUrl ?: ""
        )
    }
}