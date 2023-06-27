package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.base.BaseViewModel
import com.example.weatherapp.data.models.Weather
import com.example.weatherapp.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    BaseViewModel() {
    private val _weathers = MutableLiveData<List<Weather>>()

    val weathers: LiveData<List<Weather>>
        get() = _weathers

    fun searchWeather(query: String) {
        viewModelScope.launch(handler) {
            showLoading(true)
            weatherRepository.loadWeathers(query)
                .also {
                    showLoading(false)
                }.fold(
                    onSuccess = {
                        _weathers.value = it
                    },
                    onFailure = {
                        handleError(it)
                    }
                )
        }
    }
}