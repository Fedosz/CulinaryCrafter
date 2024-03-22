package com.example.culinarycrafter.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.culinarycrafter.models.repositories.UserRepository
import com.example.culinarycrafter.viewmodels.FridgeViewModel
import com.example.culinarycrafter.viewmodels.events.FridgeViewModelEvents

class FridgeViewModelFactory(
    private val userRepository: UserRepository,
    private val events: FridgeViewModelEvents
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FridgeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FridgeViewModel(userRepository, events) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}