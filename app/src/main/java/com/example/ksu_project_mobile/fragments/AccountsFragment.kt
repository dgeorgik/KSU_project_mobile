package com.example.ksu_project_mobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.ksu_project_mobile.R
import com.example.ksu_project_mobile.databinding.FragmentAccountsBinding
import com.example.ksu_project_mobile.databinding.FragmentApplicationStatusBinding

class AccountsFragment : Fragment() {
    private var _binding: FragmentAccountsBinding? = null
    private val binding: FragmentAccountsBinding
        get() = _binding ?: throw IllegalStateException("View binding is only available [AccountsFragment]")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountsBinding.inflate(inflater, container, false)

        val toolbar: Toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        val toolbarTitle: TextView = binding.root.findViewById(R.id.toolbar_title)
        toolbarTitle.text = "Счета"

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
