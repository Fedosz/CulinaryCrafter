package com.example.culinarycrafter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.culinarycrafter.models.repositories.UserRepository
import com.example.culinarycrafter.viewmodels.events.RecipeViewModelEvents
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val userRepository: UserRepository,
    private val events: RecipeViewModelEvents
) : ViewModel() {

    private val _isFavorite = MutableLiveData(false)
    val isFavorite: LiveData<Boolean> = _isFavorite.map { it }

    fun checkFavorite(login: String, recipeId: String) {
        viewModelScope.launch {
            _isFavorite.value = userRepository.checkFavorite(login, recipeId)
        }
    }

    fun doClick(login: String, recipeId: String) {
        if (_isFavorite.value == true) doDeleteFromFavorite(login, recipeId)
        else doAddToFavorite(login, recipeId)
    }

    private fun doAddToFavorite(login: String, recipeId: String) {
        viewModelScope.launch {
            userRepository.doAddToFavorite(login, recipeId)
            checkFavorite(login, recipeId)
        }
    }

    private fun doDeleteFromFavorite(login: String, recipeId: String) {
        viewModelScope.launch {
            userRepository.doDeleteFromFavorite(login, recipeId)
            checkFavorite(login, recipeId)
        }
    }

}