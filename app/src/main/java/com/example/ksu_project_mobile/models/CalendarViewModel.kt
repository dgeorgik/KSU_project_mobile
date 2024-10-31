package com.example.ksu_project_mobile.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ksu_project_mobile.repo.CalendarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CalendarViewModel : ViewModel() {
    private val repository = CalendarRepository()

    fun fetchMonthData(year: Int, month: Int, onResult: (List<CalendarDayResponse>) -> Unit) {
        viewModelScope.launch {
            val daysData = withContext(Dispatchers.IO) {
                repository.getMonthInfo(year, month)
            }
            onResult(daysData)
        }
    }
}
