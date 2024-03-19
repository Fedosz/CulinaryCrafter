package com.example.culinarycrafter.models.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.culinarycrafter.models.entities.UserEntity
import com.example.culinarycrafter.models.entities.UserIngredientEntity
import com.example.culinarycrafter.models.entities.UserRecipeEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM userentity WHERE login = :login")
    suspend fun checkAvailability(login: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredient(userIngredient: UserIngredientEntity)

    @Query("SELECT * FROM useringrediententity WHERE login = :login")
    suspend fun getUserIngredients(login: String): List<UserIngredientEntity>

    @Query("DELETE FROM useringrediententity WHERE id = :id")
    suspend fun delete(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userRecipe: UserRecipeEntity)

    @Query("SELECT * FROM userrecipeentity WHERE login = :login")
    suspend fun getUserRecipes(login: String): List<UserRecipeEntity>

    @Query("DELETE FROM userrecipeentity WHERE login = :login AND recipeId = :recipeId")
    suspend fun doDeleteFromFavorite(login: String, recipeId: String)

    @Query("SELECT * FROM userrecipeentity WHERE login = :login AND recipeId = :recipeId")
    suspend fun checkFavorite(login: String, recipeId: String): UserRecipeEntity?

    @Query("SELECT * FROM useringrediententity WHERE login = :login AND ingredient = :ingredient")
    suspend fun checkIngredient(login: String, ingredient: String): UserIngredientEntity?
}