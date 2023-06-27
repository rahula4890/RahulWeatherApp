package com.example.weatherapp.data.repository

import com.example.weatherapp.data.source.remote.WeatherRemoteDataSource
import com.example.weatherapp.utils.ValueConst
import com.example.weatherapp.utils.ValueConst.UNIT
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val defaultDispatcher: CoroutineDispatcher,
    private val remoteDataSource: WeatherRemoteDataSource
) {
    suspend fun loadWeathers(
        query: String,
    ) = withContext(defaultDispatcher) {
        remoteDataSource.fetchWeather(query = query, cnt = ValueConst.CNT_LENGTH, unit = UNIT)
    }
}
