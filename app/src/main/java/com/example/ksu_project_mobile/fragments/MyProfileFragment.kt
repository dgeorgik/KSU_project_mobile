package com.example.ksu_project_mobile.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.ksu_project_mobile.R
import com.example.ksu_project_mobile.databinding.FragmentMyProfileBinding
import com.example.ksu_project_mobile.databinding.FragmentWelcomeBinding
import com.example.ksu_project_mobile.models.User
import com.example.ksu_project_mobile.models.UserViewModel

class MyProfileFragment : Fragment() {
    private var _binding: FragmentMyProfileBinding? = null

//    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by activityViewModels()



    private val binding: FragmentMyProfileBinding
        get() = _binding ?: throw IllegalStateException("View binding is only available [MyProfileFragment]")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyProfileBinding.inflate(inflater, container, false)

        val toolbar: Toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        val toolbarTitle: TextView = binding.root.findViewById(R.id.toolbar_title)
        toolbarTitle.text = "Мой профиль"

        userViewModel.currentUser.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                Log.d("MyProfileFragment", "User: $user")
                binding.profileName.text = user.name
                binding.profilePosition.text = user.role
            } else {
                Log.d("MyProfileFragment", "User is null")
            }
        }
//        val user = arguments?.getParcelable<User>("user")
//
//        Log.d("MyProfileFragment", "User: ${user}")
//
//        user?.let {
//            binding.profileName.text = it.name
//            binding.profilePosition.text = it.role
//        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
