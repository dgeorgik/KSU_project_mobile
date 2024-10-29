package com.example.ksu_project_mobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ksu_project_mobile.R
import com.example.ksu_project_mobile.databinding.FragmentAccountsBinding
import com.example.ksu_project_mobile.databinding.FragmentApplicationStatusBinding
import com.example.ksu_project_mobile.databinding.FragmentAssetsBinding

class ApplicationStatusFragment : Fragment() {
    private var _binding: FragmentApplicationStatusBinding? = null

    private val binding: FragmentApplicationStatusBinding
        get() = _binding ?: throw IllegalStateException("View binding is only available [ApplicationStatusFragment]")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_application_status, container, false)
    }
}