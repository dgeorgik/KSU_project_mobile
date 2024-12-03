package com.example.ksu_project_mobile.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ksu_project_mobile.R
import com.example.ksu_project_mobile.models.UserViewModel

class EmployeesAdapter(
    private val userList: MutableList<UserEntity>,
    private val userViewModel: UserViewModel
) : RecyclerView.Adapter<EmployeesAdapter.EmployeeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_employee, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user, userViewModel, position)
    }

    override fun getItemCount(): Int = userList.size

    // ViewHolder для сотрудника
    inner class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tv_employee_name)
        private val roleSpinner: Spinner = itemView.findViewById(R.id.spinner_role)
        private val deleteButton: Button = itemView.findViewById(R.id.btn_delete)

        fun bind(user: UserEntity, userViewModel: UserViewModel, position: Int) {
            nameTextView.text = user.name

             val roles = listOf("admin", "employee", "manager")
            val adapter = ArrayAdapter(itemView.context, android.R.layout.simple_spinner_item, roles)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            roleSpinner.adapter = adapter

            val rolePosition = roles.indexOf(user.role)
            if (rolePosition >= 0) {
                roleSpinner.setSelection(rolePosition)
            }

            roleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val newRole = roles[position]
                    if (newRole != user.role) {
                        userViewModel.updateUser(user.copy(role = newRole))
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                 }
            }

             deleteButton.setOnClickListener {
                 userViewModel.deleteUser(user)

                 userList.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }
}
