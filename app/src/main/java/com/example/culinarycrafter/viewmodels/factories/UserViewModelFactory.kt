package com.example.culinarycrafter.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.culinarycrafter.models.repositories.UserRepository
import com.example.culinarycrafter.viewmodels.UserViewModel
import com.example.culinarycrafter.viewmodels.events.UserViewModelEvents

class UserViewModelFactory(
    private val userRepository: UserRepository,
    private val events: UserViewModelEvents
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userRepository, events) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}