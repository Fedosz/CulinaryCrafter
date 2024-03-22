package com.example.culinarycrafter.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.culinarycrafter.databinding.RecipesFragmentBinding
import com.example.culinarycrafter.models.MyRecipesApplication
import com.example.culinarycrafter.models.enums.CategoryEnum
import com.example.culinarycrafter.models.enums.RecipeEnum
import com.example.culinarycrafter.viewmodels.RecipesViewModel
import com.example.culinarycrafter.viewmodels.events.RecipesViewModelEvents
import com.example.culinarycrafter.viewmodels.factories.RecipesViewModelFactory
import com.example.culinarycrafter.views.adapters.CategoryAdapter
import com.example.culinarycrafter.views.adapters.RecipeAdapter

class RecipesFragment : Fragment(), RecipesViewModelEvents, RecipeAdapter.OnClickItemListener,
    CategoryAdapter.OnCheckListener {

    private var _binding: RecipesFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RecipesViewModel by viewModels {
        RecipesViewModelFactory(
            (requireActivity().application as MyRecipesApplication).userRepository,
            this
        )
    }
    private val recipeAdapter = RecipeAdapter(this)
    private val categoryAdapter = CategoryAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RecipesFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setListeners()
        addObservers()
        return root
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadRecipes((activity as MainActivity).getCurrentUser())
    }

    private fun addObservers() {
        viewModel.allRecipes.observe(viewLifecycleOwner) {
            recipeAdapter.submitList(it)
        }

        viewModel.categories.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it)
        }

        viewModel.selectedCategories.observe(viewLifecycleOwner) {
            binding.selectedCategoryTextView.text =
                "Выбранные категории: ${
                    it.joinToString(", ") { it.value }.ifEmpty { "Все" }
                }"
        }
    }

    private fun setListeners() {
        binding.recipesLayout.apply {
            adapter = recipeAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }

        binding.categoryRecyclerView.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }

        binding.filterButton.setOnClickListener {
            binding.categoryRecyclerView.visibility =
                if (binding.categoryRecyclerView.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }

        binding.searchButton.setOnClickListener {
            binding.categoryRecyclerView.visibility = View.GONE
            val recipes =
                if (viewModel.selectedCategories.value?.isEmpty() == true) viewModel.allRecipes.value
                else viewModel.allRecipes.value?.filter {
                    viewModel.selectedCategories.value?.contains(it.recipe.category) == true
                }
            recipeAdapter.submitList(recipes)
        }
    }

    override fun onClickItem(recipe: RecipeEnum) {
        RecipeActivity.start(requireActivity(), recipe)
    }

    override fun onChecked(category: CategoryEnum) {
        viewModel.doChecked(category)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = RecipesFragment()

    }

}