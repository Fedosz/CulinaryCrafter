package com.example.culinarycrafter.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.culinarycrafter.models.repositories.UserRepository
import com.example.culinarycrafter.viewmodels.RecipesViewModel
import com.example.culinarycrafter.viewmodels.events.RecipesViewModelEvents

class RecipesViewModelFactory(
    private val userRepository: UserRepository,
    private val events: RecipesViewModelEvents
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecipesViewModel(userRepository, events) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}