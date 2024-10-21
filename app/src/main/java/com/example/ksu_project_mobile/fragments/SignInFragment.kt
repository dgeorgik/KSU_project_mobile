package com.example.ksu_project_mobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ksu_project_mobile.R
import com.example.ksu_project_mobile.databinding.FragmentSigninBinding
import com.example.ksu_project_mobile.models.User
import com.example.ksu_project_mobile.models.UserViewModel
import androidx.fragment.app.activityViewModels

class SignInFragment : Fragment() {

    private var _binding: FragmentSigninBinding? = null
    private val binding get() = _binding!!
    private val userViewModel: UserViewModel by activityViewModels() // ViewModel на уровне активности

    // Предопределенный администратор
    private val adminUser = User(
        name = "Admin",
        email = "admin@email.com",
        password = "admin",
        role = "admin"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignIn.setOnClickListener {
            val email = binding.etLogin.text.toString()
            val password = binding.etPassword.text.toString()

            if (email == adminUser.email && password == adminUser.password) {
                 userViewModel.setUserName(adminUser.name)
                val bundle = Bundle().apply {
                    putParcelable("user", adminUser)
                }
                 findNavController().navigate(R.id.action_signInFragment_to_homeFragment, bundle)
            } else {
                Toast.makeText(
                    context,
                    "Неверные данные. Пожалуйста, зарегистрируйтесь.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.btnGoToSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
