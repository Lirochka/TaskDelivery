package com.example.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meals (
  var id: String,
  var title: String,
  var imageUrl: String,
) : Parcelable