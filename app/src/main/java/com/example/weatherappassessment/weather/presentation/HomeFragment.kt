package com.example.weatherappassessment.weather.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherappassessment.R
import com.example.weatherappassessment.core.util.LifeCycleUtil.collectInLifecycleScope
import com.example.weatherappassessment.core.util.formatDate
import com.example.weatherappassessment.core.util.getIcon
import com.example.weatherappassessment.core.util.hide
import com.example.weatherappassessment.core.util.orZero
import com.example.weatherappassessment.core.util.parcelable
import com.example.weatherappassessment.core.util.show
import com.example.weatherappassessment.databinding.FragmentHomeBinding
import com.example.weatherappassessment.weather.data.entity.Location
import com.example.weatherappassessment.weather.presentation.adapter.WeatherForecastAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()
    private val viewBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val adapter by lazy { WeatherForecastAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding.lifecycleOwner = viewLifecycleOwner
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeState()
    }

    private fun observeState() {
        collectInLifecycleScope(viewModel.uiState) { uiState ->
            handleLoadState(uiState)
            handleError(uiState)

//            if (uiState.currentWeather != null) {
//                updateView(uiState)
//            }
            updateView(uiState)
        }
    }

    private fun updateView(uiState: HomeUiState) {
        viewBinding.apply {
            val dailyForecast = uiState.dailyForecast
            val currentWeather = uiState.currentWeather

            if (dailyForecast == null || currentWeather == null) {
                mainContent.hide()
                noDataLayout.root.show()
            } else {
                mainContent.show()
                noDataLayout.root.hide()
                adapter.submitList(dailyForecast.list.orEmpty().toMutableList())

                val icon = currentWeather.weather?.get(0)?.getIcon
                val temperature = "${currentWeather.main?.temp}°C"
                val humidity = "${currentWeather.main?.humidity} %"
                val windSpeed = "${currentWeather.wind?.speed} m/s"

                currentLocation.text = currentWeather.name
                currentDate.text = formatDate(currentWeather.dt.orZero)
                temp.text = temperature
                weatherDesc.text = currentWeather.weather?.get(0)?.description
                highTemp.text = "H: ${currentWeather.main?.tempMax}°"
                lowTemp.text = "L: ${currentWeather.main?.tempMin}°"
                tempValue.text = temperature
                humidityValue.text = humidity
                windSpeedValue.text = windSpeed
                icon?.let { weatherIcon.setImageResource(it) }
            }


        }
    }

    private fun handleLoadState(uiState: HomeUiState) {
        viewBinding.apply {
            if (uiState.isLoading) {
                progressLayout.root.show()
                mainContent.hide()
                errorLayout.root.hide()
                noDataLayout.root.hide()
            } else {
                // handle the loaded state
                progressLayout.root.hide()
//                updateView(uiState)
            }
        }
    }

    private fun handleError(uiState: HomeUiState) {
        viewBinding.apply {
            if (uiState.currentWeather == null && uiState.errorMessage != null){
                progressLayout.root.hide()
                mainContent.hide()
                noDataLayout.root.hide()
                errorLayout.root.show()
                errorLayout.actionBtn.setOnClickListener { viewModel.getCurrentWeather() }
            } else {
                errorLayout.root.hide()
//                updateView(uiState)
            }
        }
    }

    private fun initView() {
        viewBinding.apply {
            dailyForecastList.adapter = adapter
            searchIcon.setOnClickListener {
                findNavController().navigate(R.id.searchCityFragment)
            }

            initFragmentResultListener()
        }
//        viewModel.getWeather(42.9832406, -81.243372)
    }

    private fun initFragmentResultListener() {
        setFragmentResultListener(REQUEST_KEY) { _, bundle ->
            val location = bundle.parcelable<Location>(RESULT_KEY)
            viewModel.updateWeatherCord(location?.lat.orZero, location?.lon.orZero)
        }
    }

    companion object{
        const val REQUEST_KEY = "location"
        const val RESULT_KEY = "location_result_key"
    }
}