package com.example.culinarycrafter.models.entities

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
@Table(name = "recipes_ingredient")
class RecipesIngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private val id: Long? = null

    @Column(name = "recipe", nullable = false)
    private val recipe: String? = null

    @Column(name = "ingredient", nullable = false)
    private val ingredient: String? = null
}


