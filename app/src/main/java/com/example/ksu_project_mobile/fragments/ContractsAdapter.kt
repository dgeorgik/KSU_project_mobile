package com.example.ksu_project_mobile.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ksu_project_mobile.R
import com.example.ksu_project_mobile.databinding.ItemContractBinding
import com.example.ksu_project_mobile.databinding.ItemEmployeeBinding

class ContractsAdapter(private val contracts: List<String>) : RecyclerView.Adapter<ContractsAdapter.ContractViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContractViewHolder {
        val binding = ItemContractBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContractViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContractViewHolder, position: Int) {
        holder.bind(contracts[position])
    }

    override fun getItemCount(): Int = contracts.size

    inner class ContractViewHolder(private val binding: ItemContractBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(contract: String) {
            binding.contractTextView.text = contract
        }
    }
}