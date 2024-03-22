package com.example.culinarycrafter.views

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.culinarycrafter.databinding.FavouritesFragmentBinding
import com.example.culinarycrafter.models.MyRecipesApplication
import com.example.culinarycrafter.models.enums.RecipeEnum
import com.example.culinarycrafter.viewmodels.RecipesViewModel
import com.example.culinarycrafter.viewmodels.events.RecipesViewModelEvents
import com.example.culinarycrafter.viewmodels.factories.RecipesViewModelFactory
import com.example.culinarycrafter.views.MainActivity
import com.example.culinarycrafter.views.adapters.FavoriteAdapter

class FavouritesFragment : Fragment(), RecipesViewModelEvents, FavoriteAdapter.OnClickItemListener {

    private var _binding: FavouritesFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RecipesViewModel by viewModels {
        RecipesViewModelFactory(
            (requireActivity().application as MyRecipesApplication).userRepository,
            this
        )
    }
    private val favoriteAdapter = FavoriteAdapter(this)
    private val login: String
        get() = (activity as MainActivity).getCurrentUser() ?: ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavouritesFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setListeners()
        addObservers()
        return root
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadUserRecipes(login)
    }

    override fun onClickItem(recipe: RecipeEnum) {
        AlertDialog.Builder(requireContext())
            .setTitle("Убрать рецепт \"${recipe.value}\" из избранного")
            .setPositiveButton("Да") { dialog, _ ->
                viewModel.doDeleteFromFavorite(login, recipe.id)
                dialog.dismiss()
            }.setNegativeButton("Нет") { dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }

    private fun addObservers() {
        viewModel.userRecipes.observe(viewLifecycleOwner) {
            favoriteAdapter.submitList(it)
        }
    }

    private fun setListeners() {
        binding.favoritesLayout.apply {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = FavouritesFragment()

    }

}