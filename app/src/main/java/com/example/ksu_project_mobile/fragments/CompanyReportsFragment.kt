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
import com.example.ksu_project_mobile.databinding.FragmentCompanyReportsBinding
import com.example.ksu_project_mobile.databinding.FragmentCurrentContractsBinding

class CompanyReportsFragment : Fragment() {
    private var _binding: FragmentCompanyReportsBinding? = null

    private val binding: FragmentCompanyReportsBinding
        get() = _binding ?: throw IllegalStateException("View binding is only available [CompanyReportsFragment]")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompanyReportsBinding.inflate(inflater, container, false)

        val toolbar: Toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        val toolbarTitle: TextView = binding.root.findViewById(R.id.toolbar_title)
        toolbarTitle.text = "Отчеты компании"


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}