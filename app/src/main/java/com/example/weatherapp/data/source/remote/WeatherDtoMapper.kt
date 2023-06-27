package com.example.weatherapp.data.source.remote

import com.example.weatherapp.data.models.Weather
import java.util.*

class WeatherDtoMapper {
    fun map(dto: WeatherResponseDto): List<Weather> {
        return dto.list.map {
            Weather(
                date = Date(it.dt.toLong()),
                temperature = it.temp.day,
                pressure = it.pressure,
                humidity = it.humidity,
                description = it.weather.firstOrNull()?.description ?: "",
            )
        }
    }
}