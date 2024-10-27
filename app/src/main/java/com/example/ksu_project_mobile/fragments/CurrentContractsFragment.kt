package com.example.ksu_project_mobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ksu_project_mobile.R
import com.example.ksu_project_mobile.databinding.FragmentCurrentContractsBinding

class CurrentContractsFragment : Fragment() {
    private var _binding: FragmentCurrentContractsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentContractsBinding.inflate(inflater, container, false)

        // Установка тулбара
        val toolbar: Toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        // Установка заголовка тулбара
        val toolbarTitle: TextView = binding.root.findViewById(R.id.toolbar_title)
        toolbarTitle.text = "Актуальные договора"

        // Настройка RecyclerView
        val recyclerView: RecyclerView = binding.contractsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ContractsAdapter(getContracts())

        return binding.root
    }

    private fun getContracts(): List<String> {
        return listOf(
            "Договор № 121214. Сформированный пакет докуме...",
            "Договор № 121215. Сформированный пакет докуме...",
            "Договор № 121216. Сформированный пакет докуме...",
            "Договор № 121217. Сформированный пакет докуме...",
            "Договор № 121218. Сформированный пакет докуме..."
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
