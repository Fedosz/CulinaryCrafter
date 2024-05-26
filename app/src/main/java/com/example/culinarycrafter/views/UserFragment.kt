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
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import java.util.Base64

class UserFragment : Fragment(), UserViewModelEvents {

    private var _binding: UserFragmentBinding? = null

    val key = "secretKey123456"

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
            if (login.isNotEmpty() && password.isNotEmpty()) viewModel.doLogin(login, encryptAES(password, key))
            else onShowMessage("Введите данные")
        }

        binding.logoutButton.setOnClickListener {
            viewModel.doLogout()
        }

        binding.registrationButton.setOnClickListener {
            val login = binding.loginEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.doRegistration(login, encryptAES(password, key))
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

    fun encryptAES(input: String, key: String): String {
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        val secretKeySpec = SecretKeySpec(key.toByteArray(), "AES")
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec)
        val encryptedBytes = cipher.doFinal(input.toByteArray())
        return Base64.getEncoder().encodeToString(encryptedBytes)
    }
}