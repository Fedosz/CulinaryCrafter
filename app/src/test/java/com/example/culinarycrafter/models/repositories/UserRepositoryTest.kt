package com.example.culinarycrafter.models.repositories

import com.example.culinarycrafter.models.daos.UserDao
import com.example.culinarycrafter.models.entities.UserEntity
import com.example.culinarycrafter.models.entities.UserIngredientEntity
import com.example.culinarycrafter.models.entities.UserRecipeEntity
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito
import org.mockito.Mockito.mock

public class UserRepositoryTest {
    private lateinit var database : UserDao
    private lateinit var userRepository : UserRepository

    private lateinit var testName: String
    private lateinit var testPassword: String
    private lateinit var testIngredient: String
    private lateinit var testRecipe: String

    @BeforeEach
    fun setUp() {
        testName = "testLogin"
        testPassword = "testPassword"
        testRecipe = "testRecipe"
        testIngredient = "testIngredient"
        database = mock(UserDao::class.java)
        userRepository = UserRepository(database)
    }

    @org.junit.jupiter.api.Test
    fun register() = runBlocking {
        Mockito.`when`(database.insert(UserEntity(testName, testPassword))).thenReturn(Unit)
        userRepository.register(testName, testPassword)
    }

    @org.junit.jupiter.api.Test
    fun insertIngredient() = runBlocking {
        val testItem = UserIngredientEntity(0, testName, testIngredient)
        Mockito.`when`(database.insertIngredient(testItem)).thenReturn(Unit)

        userRepository.insertIngredient(testItem)
    }

    @org.junit.jupiter.api.Test
    fun getUserIngredients() = runBlocking {
        val testItem = UserIngredientEntity(1, testName, testIngredient)
        Mockito.`when`(database.getUserIngredients(testName)).thenReturn(listOf(testItem))

        val res = userRepository.getUserIngredients(testName)
        Assertions.assertNotNull(res)
        Assertions.assertEquals(1, res[0].id)
        Assertions.assertEquals(testName, res[0].login)
        Assertions.assertEquals(testIngredient, res[0].ingredient)
    }

    @org.junit.jupiter.api.Test
    fun deleteIngredient() = runBlocking {
        Mockito.`when`(database.delete(1)).thenReturn(Unit)

        userRepository.deleteIngredient(UserIngredientEntity(1, testName, testIngredient))
    }

    @org.junit.jupiter.api.Test
    fun checkAvailability() = runBlocking{
        Mockito.`when`(database.checkAvailability(testName)).thenReturn(UserEntity(testName, testPassword))
        val res = userRepository.findByLogin(testName)
        Assertions.assertNotNull(res)
        Assertions.assertEquals(testName, res!!.login)
        Assertions.assertEquals(testPassword, res.password)
    }

    @org.junit.jupiter.api.Test
    fun doAddToFavorite() = runBlocking{
        Mockito.`when`(database.insert(UserRecipeEntity(login = testName, recipeId = testRecipe)))
            .thenReturn(Unit)

        userRepository.doAddToFavorite(testName, testRecipe)
    }

    @org.junit.jupiter.api.Test
    fun doDeleteFromFavorite() = runBlocking{
        Mockito.`when`(database.doDeleteFromFavorite(testName, testRecipe)).thenReturn(Unit)

        userRepository.doDeleteFromFavorite(testName, testRecipe)
    }

    @org.junit.jupiter.api.Test
    fun checkFavorite() = runBlocking{
        Mockito.`when`(database.checkFavorite(testName, testRecipe)).thenReturn(null)

        val bool = userRepository.checkFavorite(testName, testRecipe)
        Assertions.assertFalse(bool)
    }
}