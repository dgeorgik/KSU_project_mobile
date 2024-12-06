package com.example.ksu_project_mobile.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalendarDayResponse(
    val year: Int,
    val month: MonthInfos,
    val date: String,
    val isWorkingDay: Boolean,
    val holiday: String? = null,
    val status: Int
)



@Serializable
data class MonthInfos(
    val name: String,
    val id: Int
)