package com.example.ksu_project_mobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ksu_project_mobile.R
import com.example.ksu_project_mobile.databinding.FragmentEmployeesBinding
import com.example.ksu_project_mobile.models.UserViewModel
import kotlinx.coroutines.flow.collect

class EmployeesFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels()
    private var _binding: FragmentEmployeesBinding? = null

    private val binding: FragmentEmployeesBinding
        get() = _binding ?: throw IllegalStateException("View binding is only available [EmployeesFragment]")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.recyclerViewEmployees
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

         lifecycleScope.launchWhenStarted {
            userViewModel.users.collect { userList ->
                 val adapter = EmployeesAdapter(userList.toMutableList(), userViewModel)
                recyclerView.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
