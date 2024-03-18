package com.example.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IngredientEntity(
    @PrimaryKey
    val name: String
) {

    override fun toString(): String {
        return name
    }
}