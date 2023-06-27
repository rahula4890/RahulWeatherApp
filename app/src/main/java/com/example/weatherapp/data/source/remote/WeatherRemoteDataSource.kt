package com.example.weatherapp.data.source.remote

import com.example.weatherapp.data.models.Weather
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(
    private val service: WeatherService, private val mapper: WeatherDtoMapper
) {

    suspend fun fetchWeather(
        query: String,
        cnt: Int?,
        unit: String?,
    ): Result<List<Weather>> {
        return service.fetchWeather(query, cnt, unit).map {
            mapper.map(it)
        }
    }
}