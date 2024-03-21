package com.example.culinarycrafter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.culinarycrafter.models.entities.UserIngredientEntity
import com.example.culinarycrafter.models.enums.IngredientEnum
import com.example.culinarycrafter.models.repositories.UserRepository
import com.example.culinarycrafter.viewmodels.events.FridgeViewModelEvents
import kotlinx.coroutines.launch

class FridgeViewModel(
    private val userRepository: UserRepository,
    private val events: FridgeViewModelEvents
) : ViewModel() {

    private val _searchIngredients = MutableLiveData<List<IngredientEnum>>(emptyList())
    private val _userIngredients = MutableLiveData<List<UserIngredientEntity>>(emptyList())

    val searchIngredients: LiveData<List<IngredientEnum>> = _searchIngredients.map { it }
    val userIngredients: LiveData<List<UserIngredientEntity>> = _userIngredients.map { it }

    fun loadUserIngredients(login: String) {
        viewModelScope.launch {
            _userIngredients.value = userRepository.getUserIngredients(login)
        }
    }

    fun addIngredientToFridge(login: String, ingredient: String) {
        viewModelScope.launch {
            userRepository.insertIngredient(
                UserIngredientEntity(
                    login = login,
                    ingredient = ingredient
                )
            )
            events.onSuccessInsertIngredient()
        }
    }

    fun deleteIngredient(userIngredient: UserIngredientEntity) {
        viewModelScope.launch {
            userRepository.deleteIngredient(userIngredient)
            events.onSuccessDeleteIngredient()
        }
    }

    fun setFindIngredient(search: String) {
        _searchIngredients.value = IngredientEnum.entries.filter { it.value.contains(search, true) }
    }
}