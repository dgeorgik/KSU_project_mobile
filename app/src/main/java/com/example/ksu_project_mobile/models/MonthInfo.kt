package com.example.ksu_project_mobile.models

import kotlinx.serialization.Serializable

@Serializable
data class MonthInfo(
    val id: Int,
    val name: String,
    val workingDays: Int,
    val notWorkingDays: Int,
    val shortDays: Int,
    val workingHours: Int
)