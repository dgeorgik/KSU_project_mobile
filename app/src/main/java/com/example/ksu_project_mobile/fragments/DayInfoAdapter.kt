package com.example.ksu_project_mobile.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ksu_project_mobile.R
import com.example.ksu_project_mobile.models.CalendarDayResponse

class DayInfoAdapter(private val dayInfoList: List<CalendarDayResponse>) :
    RecyclerView.Adapter<DayInfoAdapter.DayInfoViewHolder>() {

    class DayInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateTextView: TextView = view.findViewById(R.id.dateTextView)
        val infoTextView: TextView = view.findViewById(R.id.infoTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayInfoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_day_info, parent, false)
        return DayInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayInfoViewHolder, position: Int) {
        val dayInfo = dayInfoList[position]
        holder.dateTextView.text = dayInfo.date
        holder.infoTextView.text = if (dayInfo.holiday != null) {
            "Праздник: ${dayInfo.holiday}"
        } else {
            if (dayInfo.isWorkingDay) "Рабочий день" else "Выходной"
        }
        Log.d("DayInfoAdapter", "Binding day info: $dayInfo")
    }

    override fun getItemCount() = dayInfoList.size
}
