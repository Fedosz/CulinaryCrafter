package com.example.culinarycrafter.models

import android.app.Application
import com.example.culinarycrafter.models.repositories.UserRepository

class MyRecipesApplication : Application() {

    val database by lazy { RecipeRoomDatabase.getDatabase(this) }
    val userRepository by lazy { UserRepository(database.userDao()) }


}