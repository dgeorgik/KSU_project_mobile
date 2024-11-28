package com.example.ksu_project_mobile.models

import kotlinx.serialization.Serializable

@Serializable
data class YearResponse(
    val year: Int,
    val months: List<MonthInfo>,
    val status: Int
)