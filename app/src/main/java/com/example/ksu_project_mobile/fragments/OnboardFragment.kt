package com.example.ksu_project_mobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.ksu_project_mobile.R
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide

import com.example.ksu_project_mobile.databinding.FragmentOnboardBinding
import com.example.ksu_project_mobile.databinding.FragmentWelcomeBinding

class OnboardFragment : Fragment() {

    private var _binding: FragmentOnboardBinding? = null

    private val binding: FragmentOnboardBinding
        get() = _binding ?: throw IllegalStateException("View binding is only available [OnboardFragment]")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOnboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val imageView: ImageView = view.findViewById(R.id.gif_background)

//        Glide.with(this)
//            .asGif()
//            .load(R.drawable.animation)
//            .into(imageView)
        binding.btnNextToSignIn.setOnClickListener {

            findNavController().navigate(R.id.action_onboardFragment_to_signInFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}