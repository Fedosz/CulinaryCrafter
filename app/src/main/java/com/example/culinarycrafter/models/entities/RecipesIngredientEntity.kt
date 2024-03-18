package com.example.culinarycrafter.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipesIngredientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val recipe: String,
    val ingredient: String
) {
}
