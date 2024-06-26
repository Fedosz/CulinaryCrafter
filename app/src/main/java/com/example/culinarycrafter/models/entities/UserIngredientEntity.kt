package com.example.culinarycrafter.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserIngredientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val login: String,
    val ingredient: String
) {
}
