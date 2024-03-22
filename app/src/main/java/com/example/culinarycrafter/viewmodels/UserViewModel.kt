package com.example.culinarycrafter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.culinarycrafter.models.repositories.UserRepository
import com.example.culinarycrafter.viewmodels.events.UserViewModelEvents
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository,
    private val events: UserViewModelEvents
) : ViewModel() {

    private val _currentUser = MutableLiveData<String?>(null)
    val currentUser: LiveData<String?> = _currentUser.map { it }

    fun doLogin(login: String, password: String) {
        viewModelScope.launch {
            val userExist = userRepository.checkAvailability(login)
            if (userExist == null) {
                events.onShowMessage("Пользователя не существует")
            } else if (userExist.password == password) {
                events.doLogin(login)
                _currentUser.value = login
            } else events.onShowMessage("Неверный пароль")
        }
    }

    fun doRegistration(login: String, password: String) {
        viewModelScope.launch {
            val userExist = userRepository.checkAvailability(login)
            if (userExist == null) {
                userRepository.login(login, password)
                events.doLogin(login)
                _currentUser.value = login
            } else {
                events.onShowMessage("Пользователь уже существует")
            }
        }
    }

    fun doLogout() {
        viewModelScope.launch {
            _currentUser.value = null
            events.doLogout()
        }
    }

    fun setCurrentUser(login: String?) {
        _currentUser.value = login
    }

}