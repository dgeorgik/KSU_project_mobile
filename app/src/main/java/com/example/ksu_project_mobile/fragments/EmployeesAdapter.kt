package com.example.ksu_project_mobile.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ksu_project_mobile.R
import com.example.ksu_project_mobile.databinding.FragmentCurrentContractsBinding
import com.example.ksu_project_mobile.databinding.FragmentEmployeesBinding
import com.example.ksu_project_mobile.databinding.ItemEmployeeBinding
import com.example.ksu_project_mobile.models.User
import com.example.ksu_project_mobile.models.UserViewModel

class EmployeesAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<EmployeesAdapter.EmployeeViewHolder>() {

    private val roles = arrayOf("Незарегистрированный", "Бухгалтер", "Менеджер", "Администратор")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = userList.size

    inner class EmployeeViewHolder(private val binding: ItemEmployeeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.tvEmployeeName.text = user.name

            val adapter = ArrayAdapter(itemView.context, android.R.layout.simple_spinner_item, roles)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerRole.adapter = adapter

            val currentRoleIndex = roles.indexOf(user.role)
            if (currentRoleIndex >= 0) {
                binding.spinnerRole.setSelection(currentRoleIndex)
            }

            binding.spinnerRole.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val selectedRole = roles[position]
                    if (selectedRole != user.role) {
                        (itemView.context as? FragmentActivity)?.let { activity ->
                            val userViewModel: UserViewModel by activity.viewModels()
                            userViewModel.updateUserRole(user, selectedRole)
                            Toast.makeText(activity, "Роль изменена на $selectedRole", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }
    }
}
