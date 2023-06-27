package com.example.weatherapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel : ViewModel() {

    private val _error = MutableLiveData<String>()

    val error: LiveData<String>
        get() = _error

    private val _loading = MutableLiveData<Boolean>()

    val loading: LiveData<Boolean>
        get() = _loading

    val handler = CoroutineExceptionHandler { _, exception ->
        handleError(exception)
    }

    fun handleError(exception: Throwable) {
        exception.printStackTrace()
        _error.postValue(exception.message)
    }

    fun showLoading(it: Boolean) {
        _loading.postValue(it)
    }

}