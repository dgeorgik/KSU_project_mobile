package com.example.ksu_project_mobile.fragments


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ksu_project_mobile.R
import com.example.ksu_project_mobile.databinding.FragmentWelcomeBinding
import com.example.ksu_project_mobile.models.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = arguments?.getParcelable<User>("user")
        user?.let {
            binding.tvUserName.text = "Добро пожаловать, ${it.name}!"
        }

        lifecycleScope.launch {
            delay(3000)
            findNavController().navigate(R.id.action_welcomeFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
