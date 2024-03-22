package com.example.culinarycrafter.views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.culinarycrafter.databinding.RecipeActivityBinding
import com.example.culinarycrafter.models.MyRecipesApplication
import com.example.culinarycrafter.models.enums.RecipeEnum
import com.example.culinarycrafter.viewmodels.RecipeViewModel
import com.example.culinarycrafter.viewmodels.events.RecipeViewModelEvents
import com.example.culinarycrafter.viewmodels.factories.RecipeViewModelFactory

class RecipeActivity : AppCompatActivity(), RecipeViewModelEvents {

    private lateinit var binding: RecipeActivityBinding
    lateinit var settings: SharedPreferences
    private val viewModel: RecipeViewModel by viewModels {
        RecipeViewModelFactory(
            (application as MyRecipesApplication).userRepository,
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecipeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        settings = getSharedPreferences(MainActivity.PREFERENCES_NAME, MODE_PRIVATE)
        setListeners()
        addObservers()
        checkFavorite(getCurrentUser(), intent.getStringExtra(RECIPE_ID)!!)
    }

    private fun checkFavorite(currentUser: String?, recipeId: String) {
        if (currentUser == null) {
            Toast.makeText(this, "Авторизуйтесь", Toast.LENGTH_LONG).show()
        } else {
            viewModel.checkFavorite(currentUser, recipeId)
        }
    }

    private fun setListeners() {
        val recipe = RecipeEnum.entries.find { it.id == intent.getStringExtra(RECIPE_ID)!! }!!
        binding.nameTextView.text = recipe.value
        binding.photoImageView.setImageResource(recipe.photo)
        binding.descriptionTextView.text = recipe.description
        binding.ingredientsTextView.text = recipe.ingredients.joinToString("\n") { it.value }
        binding.processTextView.text = recipe.process

        binding.addingToFavorite.setOnClickListener {
            getCurrentUser()?.let { login ->
                viewModel.doClick(login, recipe.id)
            } ?: run { Toast.makeText(this, "Авторизуйтесь", Toast.LENGTH_LONG).show() }

        }
    }

    private fun addObservers() {
        viewModel.isFavorite.observe(this) {
            binding.addingToFavorite.text =
                if (it) "Убрать из избранного" else "Добавить в избранное"
        }
    }

    private fun getCurrentUser(): String? {
        return settings.getString(MainActivity.USER_LOGIN, null)
    }

    companion object {
        private val RECIPE_ID = "RecipeId"

        fun start(context: Context, recipe: RecipeEnum) {
            context.startActivity(
                Intent(context, RecipeActivity::class.java).apply {
                    putExtra(RECIPE_ID, recipe.id)
                }
            )
        }

    }
}