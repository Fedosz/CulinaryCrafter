package com.example.culinarycrafter.models.repositories

import com.example.culinarycrafter.models.daos.UserDao
import com.example.culinarycrafter.models.entities.UserEntity
import com.example.culinarycrafter.models.entities.UserIngredientEntity
import com.example.culinarycrafter.models.entities.UserRecipeEntity
import com.example.culinarycrafter.models.enums.RecipeEnum

class UserRepository(private val userDao: UserDao) {

    suspend fun register(login: String, password: String) {
        userDao.insert(UserEntity(login = login, password = password))
    }

    suspend fun findByLogin(login: String): UserEntity? {
        return userDao.checkAvailability(login)
    }

    suspend fun insertIngredient(userIngredient: UserIngredientEntity) {
        if (userDao.checkIngredient(
                userIngredient.login,
                userIngredient.ingredient
            ) == null
        ) userDao.insertIngredient(userIngredient)
    }

    suspend fun getUserIngredients(login: String): List<UserIngredientEntity> {
        return userDao.getUserIngredients(login)
    }

    suspend fun getUserRecipes(login: String): List<RecipeEnum> {
        return userDao.getUserRecipes(login).mapNotNull { userRecipe ->
            RecipeEnum.entries.find { it.id == userRecipe.recipeId }
        }
    }

    suspend fun deleteIngredient(userIngredient: UserIngredientEntity) {
        userDao.delete(userIngredient.id)
    }

    suspend fun doAddToFavorite(login: String, recipeId: String) {
        userDao.insert(UserRecipeEntity(login = login, recipeId = recipeId))
    }

    suspend fun doDeleteFromFavorite(login: String, recipeId: String) {
        userDao.doDeleteFromFavorite(login, recipeId)
    }

    suspend fun checkFavorite(login: String, recipeId: String): Boolean {
        return (userDao.checkFavorite(login, recipeId) != null)
    }

}