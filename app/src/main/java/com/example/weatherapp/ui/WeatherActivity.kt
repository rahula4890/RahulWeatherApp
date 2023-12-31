package com.example.weatherapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityWeatherBinding
import com.example.weatherapp.ui.adapter.WeatherAdapter
import com.example.weatherapp.utils.ValueConst.QUERY_LIMIT_LENGTH
import com.example.weatherapp.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityWeatherBinding
    private var adapter: WeatherAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWeatherBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        onInit()

        onObserver()
    }

    private fun onInit() = with(binding) {
        adapter = WeatherAdapter()
        rvWeather.layoutManager = LinearLayoutManager(this@WeatherActivity)
        rvWeather.adapter = adapter

        etSearch.doOnTextChanged { text, _, _, _ ->
            btnSearch.isEnabled = (text?.trim()?.length ?: 0) >= QUERY_LIMIT_LENGTH
        }

        btnSearch.setOnClickListener {
            viewModel.searchWeather(etSearch.text.toString().trim())
            etSearch.setText("")
            adapter?.clear()
        }
    }

    private fun onObserver() {
        viewModel.weathers.observe(this) {
            adapter?.submitList(it)
        }

        viewModel.loading.observe(this) {
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) {
            Toast.makeText(this, getString(R.string.general_error, it), Toast.LENGTH_LONG).show()
        }
    }
}

