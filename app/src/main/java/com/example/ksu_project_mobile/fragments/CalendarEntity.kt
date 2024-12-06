package com.example.ksu_project_mobile.fragments

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ksu_project_mobile.models.CalendarDayResponse
import com.example.ksu_project_mobile.models.MonthInfos

@Entity(tableName = "calendar_day")
data class CalendarDayEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val year: Int,
    val month: Int,
    val day: String,
    val isWorkingDay: Boolean,
    val holiday: String? = null
)
fun CalendarDayEntity.toCalendarDayResponse(): CalendarDayResponse {
    return CalendarDayResponse(
        year = this.year,
        month = MonthInfos(name = this.month.toString(), id = this.month), // Месяц уже Int, так что можно передать напрямую
        date = this.day,
        isWorkingDay = this.isWorkingDay,
        holiday = this.holiday,
        status = 200 // Используем статус, который есть у `CalendarDayResponse`
    )
}
