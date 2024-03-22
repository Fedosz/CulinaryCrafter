package com.example.culinarycrafter.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.culinarycrafter.databinding.UserFragmentBinding
import com.example.culinarycrafter.models.MyRecipesApplication
import com.example.culinarycrafter.viewmodels.UserViewModel
import com.example.culinarycrafter.viewmodels.events.UserViewModelEvents
import com.example.culinarycrafter.viewmodels.factories.UserViewModelFactory

class UserFragment : Fragment(), UserViewModelEvents {

    private var _binding: UserFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory(
            (requireActivity().application as MyRecipesApplication).userRepository,
            this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setListeners()
        addObservers()
        return root
    }

    override fun onResume() {
        super.onResume()
        viewModel.setCurrentUser((activity as MainActivity).getCurrentUser())
    }

    private fun addObservers() {
        viewModel.currentUser.observe(viewLifecycleOwner) {
            binding.loginLayout.visibility = if (it != null) View.GONE else View.VISIBLE
            binding.logoutLayout.visibility = if (it != null) View.VISIBLE else View.GONE
            binding.loginTextView.text = it
        }
    }

    private fun setListeners() {
        binding.loginButton.setOnClickListener {
            val login = binding.loginEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            if (login.isNotEmpty() && password.isNotEmpty()) viewModel.doLogin(login, password)
            else onShowMessage("Введите данные")
        }

        binding.logoutButton.setOnClickListener {
            viewModel.doLogout()
        }

        binding.registrationButton.setOnClickListener {
            val login = binding.loginEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.doRegistration(login, password)
        }
    }

    override fun onShowMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun doLogin(login: String) {
        binding.loginEditText.setText("")
        binding.passwordEditText.setText("")
        (requireActivity() as? MainActivity)?.saveCurrentUser(login)
    }

    override fun doLogout() {
        (requireActivity() as? MainActivity)?.saveCurrentUser(null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = UserFragment()

    }
}