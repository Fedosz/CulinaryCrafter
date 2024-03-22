package com.example.culinarycrafter.views

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.culinarycrafter.R
import com.example.culinarycrafter.databinding.MainActivityBinding
import com.example.culinarycrafter.views.RecipesFragment
import com.example.culinarycrafter.views.UserFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    lateinit var settings: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        settings = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)
        setupNavigation()
    }

    fun saveCurrentUser(login: String?) {
        val prefEditor = settings.edit()
        prefEditor?.putString(USER_LOGIN, login)
        prefEditor?.apply()
    }

    fun getCurrentUser(): String? {
        return settings.getString(USER_LOGIN, null)
    }

    private fun setupNavigation() {
        replaceFragment(RecipesFragment.newInstance())
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            replaceFragment(
                when (it.itemId) {
                    R.id.navigation_recipes -> RecipesFragment.newInstance()
                    R.id.navigation_fridge -> FridgeFragment.newInstance()
                    R.id.navigation_favourites -> FavouritesFragment.newInstance()
                    R.id.navigation_user -> UserFragment.newInstance()
                    else -> RecipesFragment.newInstance()
                }
            )
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

    companion object {
        const val PREFERENCES_NAME = "PreferencesName"
        const val USER_LOGIN = "UserLogin"
    }

}