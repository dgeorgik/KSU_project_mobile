package com.example.ksu_project_mobile.models

import android.util.Log
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
            val totalDays = withContext(Dispatchers.IO) {
                val monthsInfo = repository.fetchYearInfo(year)
                monthsInfo.getOrNull(month-1)?.let { it.workingDays + it.notWorkingDays } ?: 0
            }

            if (totalDays > 0) {
                val daysData = withContext(Dispatchers.IO) {
                    Log.d("month " + month, "day " + totalDays)
                    repository.getMonthInfo(year, month , totalDays)
                }
                onResult(daysData)
            } else {
                onResult(emptyList())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.close()
    }

}