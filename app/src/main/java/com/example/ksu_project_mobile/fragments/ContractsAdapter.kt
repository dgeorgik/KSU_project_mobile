package com.example.ksu_project_mobile.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ksu_project_mobile.R

class ContractsAdapter(private val contracts: List<String>) : RecyclerView.Adapter<ContractsAdapter.ContractViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContractViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contract, parent, false)
        return ContractViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContractViewHolder, position: Int) {
        holder.bind(contracts[position])
    }

    override fun getItemCount(): Int = contracts.size

    inner class ContractViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val contractTextView: TextView = itemView.findViewById(R.id.contract_text_view)

        fun bind(contract: String) {
            contractTextView.text = contract
        }
    }
}
