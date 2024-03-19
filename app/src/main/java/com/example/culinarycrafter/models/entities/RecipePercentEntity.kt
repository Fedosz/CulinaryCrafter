package com.example.culinarycrafter.models.entities

import com.example.culinarycrafter.models.enums.RecipeEnum

data class RecipePercentEntity(
    val recipe: RecipeEnum,
    val percent: Int
)
