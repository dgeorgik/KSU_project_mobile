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
    private val userViewModel: UserViewModel by activityViewModels()


    private val binding: FragmentSigninBinding
        get() = _binding ?: throw IllegalStateException("View binding is only available [SignInFragment]")

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

            val user = userViewModel.authenticateUser(email, password)


            if (user != null) {
                if (user.role == "anautorize") {
                    Toast.makeText(context, "Ждите одобрение заявки или зарегисрируйтесь!", Toast.LENGTH_SHORT).show()
                } else {
                    userViewModel.setUserName(user.name)
                    val bundle = Bundle().apply {
                        putParcelable("user", user)
                    }
                    findNavController().navigate(R.id.action_signInFragment_to_homeFragment, bundle)
                }
//            if (email == adminUser.email && password == adminUser.password ) {
//                userViewModel.setUserName(adminUser.name)
//                val bundle = Bundle().apply {
//                    putParcelable("user", adminUser)
//                }
//                 findNavController().navigate(R.id.action_signInFragment_to_homeFragment, bundle)
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
