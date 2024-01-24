package com.stopstone.payapp.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

object DateFormatText {
    fun getCurrentDate(): String {
        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd hh:mm:ss", Locale.KOREA)
        val currentDate = Calendar.getInstance(TimeZone.getTimeZone("UTC")).time
        return simpleDateFormat.format(currentDate)
    }
}