package com.example.weatherapp.data.models

import java.util.Date

data class Weather(
    val date: Date,
    val temperature: Double,
    val pressure: Int,
    val humidity: Int,
    val description: String
)