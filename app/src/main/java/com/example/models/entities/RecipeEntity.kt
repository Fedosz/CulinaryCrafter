package com.example.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeEntity(
    @PrimaryKey
    val name: String,
    val description: String,
    val photo: Int
) {}