package com.example.todo.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtils {
    fun getCurrentDateTimeString(): String {
        // Get current LocalDateTime
        val currentDateTime = LocalDateTime.now()

        // Define the formatter for the desired format
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

        // Format the current datetime to string
        val formattedDateTime = currentDateTime.format(formatter)

        return formattedDateTime
    }

    fun formatDate(inputDateString:String): String{
        // Parse the input date string to an Instant
        val instant = Instant.parse(inputDateString)

        // Convert the Instant to LocalDateTime in your timezone
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

        // Define a custom date time formatter
        val formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")

        // Format the LocalDateTime using the formatter
        val formattedDateTime = localDateTime.format(formatter)

        return formattedDateTime
    }
}