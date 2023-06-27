package com.example.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val FORMAT_DATE = "dd/MM/yyyy hh:mm"
val dateFormat = SimpleDateFormat(FORMAT_DATE, Locale.getDefault())

fun Date.formatDate(): String? {
    return dateFormat.format(this)
}