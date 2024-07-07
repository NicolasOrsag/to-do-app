package com.example.todo.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtils {
    fun getCurrentDateTimeString(): String {
        val currentDateTime = ZonedDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
        return currentDateTime.format(formatter)
    }
}