package com.example.ksu_project_mobile.models

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ksu_project_mobile.fragments.AppDatabase
import com.example.ksu_project_mobile.fragments.CalendarDayDao
import com.example.ksu_project_mobile.fragments.CalendarDayEntity
import com.example.ksu_project_mobile.repo.CalendarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class CalendarViewModel(application: Application) : AndroidViewModel(application) {
//    private val repository = CalendarRepository(application.applicationContext)
private val repository = CalendarRepository()
    private val calendarDayDao: CalendarDayDao = AppDatabase.getInstance(application).calendarDao()


    fun getCalendarDaysFromDatabase(year: Int, month: Int): List<CalendarDayEntity> {
        return runBlocking(Dispatchers.IO) {
            calendarDayDao.getCalendarDaysForMonth(year, month)
        }
    }
    fun saveCalendarDays(calendarDays: List<CalendarDayEntity>) {
        viewModelScope.launch {
            calendarDayDao.insertAll(calendarDays)
        }
    }

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