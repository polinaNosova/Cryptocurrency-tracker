package com.ua.epam.ctiptocurrencytracker.viemodel

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class CalculateTime {
    fun calculateCurrentDay(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return dateFormat.format(calendar.time)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun calculatePreviousDay():String {
        return LocalDate.parse(calculateCurrentDay()).minusDays(1).toString()
    }
}