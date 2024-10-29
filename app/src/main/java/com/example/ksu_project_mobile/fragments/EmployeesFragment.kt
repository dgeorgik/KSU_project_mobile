package com.example.ksu_project_mobile.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ksu_project_mobile.R
import com.example.ksu_project_mobile.databinding.FragmentEmployeesBinding
import com.example.ksu_project_mobile.databinding.FragmentHomeBinding
import com.example.ksu_project_mobile.models.UserViewModel

class EmployeesFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels()
    private var _binding: FragmentEmployeesBinding? = null

    private val binding: FragmentEmployeesBinding
        get() = _binding ?: throw IllegalStateException("View binding is only available [EmployeesFragment]")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_employees, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_employees)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        userViewModel.users.observe(viewLifecycleOwner) { userList ->
            recyclerView.adapter = EmployeesAdapter(userList)
        }
    }
}