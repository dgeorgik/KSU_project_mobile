package com.example.ksu_project_mobile.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ksu_project_mobile.R
import com.example.ksu_project_mobile.databinding.FragmentSigninBinding
import com.example.ksu_project_mobile.databinding.FragmentSignupBinding
import com.example.ksu_project_mobile.models.User
import com.example.ksu_project_mobile.models.UserViewModel

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val userViewModel: UserViewModel by activityViewModels()


    private val binding: FragmentSignupBinding
        get() = _binding ?: throw IllegalStateException("View binding is only available [SignUpFragment]")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener {
            val name = binding.etSignupName.text.toString()
            val email = binding.etSignupEmail.text.toString()
            val password = binding.etSignupPassword.text.toString()

            val newUser = User(name = name, email = email, password = password, role = "anautorize")
            userViewModel.addUser(newUser)

            val bundle = Bundle().apply {
                putParcelable("user", newUser)
            }
            findNavController().navigate(R.id.action_applicationStatusFragment_to_welcomeFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}