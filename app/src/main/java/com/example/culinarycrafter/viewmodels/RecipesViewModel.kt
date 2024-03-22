package com.example.culinarycrafter.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.culinarycrafter.models.entities.RecipePercentEntity
import com.example.culinarycrafter.models.enums.CategoryEnum
import com.example.culinarycrafter.models.enums.RecipeEnum
import com.example.culinarycrafter.models.repositories.UserRepository
import com.example.culinarycrafter.viewmodels.events.RecipesViewModelEvents
import kotlinx.coroutines.launch

class RecipesViewModel(
    private val userRepository: UserRepository,
    private val events: RecipesViewModelEvents
) : ViewModel() {

    private val _categories = MutableLiveData(CategoryEnum.entries)
    private val _allRecipes = MutableLiveData<List<RecipePercentEntity>>(emptyList())
    private val _userRecipes = MutableLiveData<List<RecipeEnum>>(emptyList())

    val categories: LiveData<List<CategoryEnum>> = _categories.map { it }
    val selectedCategories: LiveData<List<CategoryEnum>> = _categories.map { it.filter { it.isChecked } }
    val allRecipes: LiveData<List<RecipePercentEntity>> = _allRecipes.map { it }
    val userRecipes: LiveData<List<RecipeEnum>> = _userRecipes.map { it }

    fun loadRecipes(login: String?) {
        viewModelScope.launch {
            val recipes = RecipeEnum.entries
            val ingredients =
                if (login == null) listOf() else userRepository.getUserIngredients(login)
            val recipePercentList = mutableListOf<RecipePercentEntity>()
            recipes.forEach { recipe ->
                val size100 = recipe.ingredients.size
                val sizeX =
                    ingredients.filter { it.ingredient in recipe.ingredients.map { it.value } }.size
                val percent100 = 100
                val percentX = (sizeX * percent100) / size100
                recipePercentList.add(RecipePercentEntity(recipe, percentX))
            }
            _allRecipes.value = recipePercentList.sortedByDescending { it.percent }
        }
    }

    fun loadUserRecipes(login: String) {
        viewModelScope.launch {
            _userRecipes.value = userRepository.getUserRecipes(login)
        }
    }

    fun doDeleteFromFavorite(login: String, recipeId: String) {
        viewModelScope.launch {
            userRepository.doDeleteFromFavorite(login, recipeId)
            loadUserRecipes(login)
        }
    }

    fun doChecked(category: CategoryEnum) {
        _categories.value = _categories.value?.onEach {
            if (it == category) it.isChecked = category.isChecked.not()
        }
    }

}