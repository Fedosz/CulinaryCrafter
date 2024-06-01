package com.example.culinarycrafter.models.entities

import com.example.culinarycrafter.models.enums.RecipeEnum

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipe_percent")
class RecipePercentEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "recipe", nullable = false)
    private val recipe: RecipeEnum? = null

    @Column(name = "percent", nullable = false)
    private val percent = 0
}

