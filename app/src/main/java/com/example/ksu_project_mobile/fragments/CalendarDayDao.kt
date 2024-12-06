package com.example.ksu_project_mobile.fragments

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CalendarDayDao {
    @Insert
    suspend fun insertCalendarDay(calendarDayEntity: CalendarDayEntity)

    @Insert
    suspend fun insert(calendarDay: CalendarDayEntity)

    @Insert
    suspend fun insertAll(calendarDays: List<CalendarDayEntity>)


    @Query("SELECT * FROM calendar_day WHERE year = :year AND month = :month AND day = :day")
    suspend fun getCalendarDay(year: Int, month: Int, day: kotlin.String): CalendarDayEntity?

    @Query("SELECT * FROM calendar_day WHERE year = :year AND month = :month")
    fun getCalendarDaysForMonth(year: Int, month: Int): List<CalendarDayEntity>

    @Query("SELECT * FROM calendar_day WHERE year = :year AND month = :month")
    suspend fun getDaysInMonth(year: Int, month: Int): List<CalendarDayEntity>
}