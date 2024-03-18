package com.example.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UsersRecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val login: String,
    val recipeId: Long
) {

}
