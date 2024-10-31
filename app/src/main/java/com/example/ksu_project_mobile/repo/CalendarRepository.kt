package com.example.ksu_project_mobile.repo

import com.example.ksu_project_mobile.models.CalendarDayResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class CalendarRepository {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }

    suspend fun getDayInfo(year: Int, month: Int, day: Int): CalendarDayResponse {
        val url = "https://calendar.kuzyak.in/api/calendar/$year/$month/$day"
        return client.get(url).body()
    }

    suspend fun getMonthInfo(year: Int, month: Int): List<CalendarDayResponse> {
        val days = mutableListOf<CalendarDayResponse>()
        for (day in 1..30) {
            val dayInfo = getDayInfo(year, month, day)
            days.add(dayInfo)
        }
        return days
    }
}
