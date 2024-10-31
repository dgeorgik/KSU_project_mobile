package com.example.ksu_project_mobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ksu_project_mobile.R
import com.example.ksu_project_mobile.models.CalendarDayResponse
import com.example.ksu_project_mobile.models.CalendarViewModel
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment : Fragment() {
    private val viewModel: CalendarViewModel by viewModels()
    private lateinit var dayInfoAdapter: DayInfoAdapter
    private val dayInfoList = mutableListOf<CalendarDayResponse>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val daysRecyclerView = view.findViewById<RecyclerView>(R.id.daysRecyclerView)
        dayInfoAdapter = DayInfoAdapter(dayInfoList)
        daysRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        daysRecyclerView.adapter = dayInfoAdapter

        val composeView = view.findViewById<ComposeView>(R.id.composeCalendarView)
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2024)
            set(Calendar.MONTH, Calendar.OCTOBER)
        }

        fun loadMonthData(year: Int, month: Int) {
            viewModel.fetchMonthData(year, month) { days ->
                dayInfoList.clear()
                dayInfoList.addAll(days)
                dayInfoAdapter.notifyDataSetChanged()
                composeView.setContent {
                    CalendarScreen(
                        calendar = calendar,
                        daysInMonth = dayInfoList,
                        onPreviousMonth = {
                            calendar.add(Calendar.MONTH, -1)
                            loadMonthData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1)
                        },
                        onNextMonth = {
                            calendar.add(Calendar.MONTH, 1)
                            loadMonthData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1)
                        }
                    )
                }
            }
        }

        loadMonthData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1)
    }
}

@Composable
fun CalendarScreen(
    calendar: Calendar,
    daysInMonth: List<CalendarDayResponse>,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    val monthFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
    val monthTitle = monthFormat.format(calendar.time)

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onPreviousMonth) { Text(text = "<") }

            Text(
                text = monthTitle,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Button(onClick = onNextMonth) { Text(text = ">") }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .height(350.dp)
        ) {
            items(7) { index ->
                Text(
                    text = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")[index],
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            val firstDayOfWeek = calendar.apply { set(Calendar.DAY_OF_MONTH, 1) }.get(Calendar.DAY_OF_WEEK) - 1
            items(firstDayOfWeek) { Spacer(modifier = Modifier.size(40.dp)) }

            items(daysInMonth.size) { index ->
                val day = daysInMonth[index].date.split("-")[2].split("T")[0]
                Text(
                    text = day,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(40.dp),
                    fontSize = 16.sp,
                    color = if (daysInMonth[index].isWorkingDay) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text(text = "Отобразить списком")
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Дни месяца") },
                text = {
                    Column {
                        daysInMonth.forEach { day ->
                            val dayType = when {
                                !day.isWorkingDay && day.holiday != null -> "Праздник: " + day.holiday
                                day.isWorkingDay -> "Рабочий день"
                                else -> "Выходной"
                            }
                            Text(text = "${day.date.split("T")[0]} - $dayType")
                        }
                    }
                },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Закрыть")
                    }
                }
            )
        }
    }
}
