package com.example.culinarycrafter.views

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.culinarycrafter.databinding.FridgeFragmentBinding
import com.example.culinarycrafter.models.MyRecipesApplication
import com.example.culinarycrafter.models.entities.UserIngredientEntity
import com.example.culinarycrafter.models.enums.IngredientEnum
import com.example.culinarycrafter.viewmodels.FridgeViewModel
import com.example.culinarycrafter.viewmodels.events.FridgeViewModelEvents
import com.example.culinarycrafter.viewmodels.factories.FridgeViewModelFactory
import com.example.culinarycrafter.views.MainActivity
import com.example.culinarycrafter.views.adapters.FindIngredientAdapter
import com.example.culinarycrafter.views.adapters.UserIngredientAdapter

class FridgeFragment : Fragment(), FridgeViewModelEvents,
    UserIngredientAdapter.OnClickDeleteListener, FindIngredientAdapter.OnClickItemListener {

    private var _binding: FridgeFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FridgeViewModel by viewModels {
        FridgeViewModelFactory(
            (requireActivity().application as MyRecipesApplication).userRepository,
            this
        )
    }
    private val userIngredientAdapter = UserIngredientAdapter(this)
    private val findIngredientAdapter = FindIngredientAdapter(this)
    private val login: String
        get() = (activity as MainActivity).getCurrentUser() ?: ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FridgeFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setListeners()
        addObservers()
        return root
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadUserIngredients(login)
    }

    override fun onSuccessInsertIngredient() {
        viewModel.loadUserIngredients(login)
    }

    override fun onSuccessDeleteIngredient() {
        viewModel.loadUserIngredients(login)
    }

    override fun onClickDelete(userIngredient: UserIngredientEntity) {
        AlertDialog.Builder(requireContext())
            .setTitle("Убрать ингредиент \"${userIngredient.ingredient}\" из холодильника")
            .setPositiveButton("Да") { dialog, _ ->
                viewModel.deleteIngredient(userIngredient)
                dialog.dismiss()
            }.setNegativeButton("Нет") { dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }

    private fun addObservers() {
        viewModel.userIngredients.observe(viewLifecycleOwner) {
            userIngredientAdapter.submitList(it)
        }

        viewModel.searchIngredients.observe(viewLifecycleOwner) {
            findIngredientAdapter.submitList(it)
        }
    }

    private fun setListeners() {
        binding.findIngredientEditText.addTextChangedListener {
            viewModel.setFindIngredient(it.toString())
        }

        binding.ingredientRecyclerView.apply {
            adapter = userIngredientAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }

        binding.findIngredientRecyclerView.apply {
            adapter = findIngredientAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }


    override fun onClickItem(ingredient: IngredientEnum) {
        if (login.isNotEmpty()) viewModel.addIngredientToFridge(login, ingredient.value)
        else Toast.makeText(requireContext(), "Авторизуйтесь", Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = FridgeFragment()

    }

}