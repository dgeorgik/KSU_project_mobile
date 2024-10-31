package com.example.ksu_project_mobile.models

import kotlinx.serialization.Serializable

@Serializable
data class CalendarDayResponse(
    val year: Int,
    val month: Month,
    val date: String,
    val isWorkingDay: Boolean,
    val holiday: String? = null
)