package com.example.ksu_project_mobile.repo

import android.util.Log
import com.example.ksu_project_mobile.models.CalendarDayResponse
import com.example.ksu_project_mobile.models.MonthInfo
import com.example.ksu_project_mobile.models.YearResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.json.Json

class CalendarRepository {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
        HttpResponseValidator {
            handleResponseExceptionWithRequest { exception, _ ->
                if (exception is HttpResponse && exception.status.value == 400) {
                    println("Invalid day or invalid request")
                }
            }
        }
    }

    suspend fun fetchYearInfo(year: Int): List<MonthInfo> {
        val url = "https://calendar.kuzyak.in/api/calendar/$year"
        val response = client.get(url).body<YearResponse>()
        return response.months
    }

    suspend fun getMonthInfo(year: Int, month: Int, totalDays: Int): List<CalendarDayResponse> = coroutineScope {
        val dayRequests = (1..totalDays).map { day ->
            async {
                try {
                    getDayInfo(year, month, day)
                } catch (e: Exception) {
                    println("Error fetching data for day $day: ${e.message}")
                    null
                }
            }
        }

        dayRequests.awaitAll().filterNotNull()
    }

    private suspend fun getDayInfo(year: Int, month: Int, day: Int): CalendarDayResponse {
        val url = "https://calendar.kuzyak.in/api/calendar/$year/$month/$day"
        val response = client.get(url)
        return if (response.status.isSuccess()) {
            response.body()
        } else {
            throw IllegalArgumentException("Invalid day or request for date: $year-$month-$day")
        }
    }

    fun close() {
        client.close()
    }
}
