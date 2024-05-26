package com.example.culinarycrafter.models.entities

import androidx.room.Entity
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
@Table(name = "user_recipe")
class UserRecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private val id: Long? = null

    @Column(name = "login", nullable = false)
    private val login: String? = null

    @Column(name = "recipe_id", nullable = false)
    private val recipeId: String? = null
}

