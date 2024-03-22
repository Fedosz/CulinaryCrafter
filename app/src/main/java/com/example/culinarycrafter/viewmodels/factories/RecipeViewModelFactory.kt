package com.example.culinarycrafter.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.culinarycrafter.models.enums.RecipeEnum
import com.example.culinarycrafter.models.repositories.UserRepository
import com.example.culinarycrafter.viewmodels.RecipeViewModel
import com.example.culinarycrafter.viewmodels.events.RecipeViewModelEvents

class RecipeViewModelFactory(
    private val userRepository: UserRepository,
    private val events: RecipeViewModelEvents
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecipeViewModel(userRepository, events) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}