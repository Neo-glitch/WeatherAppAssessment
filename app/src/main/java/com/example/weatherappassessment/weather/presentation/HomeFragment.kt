package com.example.weatherappassessment.weather.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherappassessment.core.util.LifeCycleUtil.collectInLifecycleScope
import com.example.weatherappassessment.databinding.FragmentHomeBinding
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

        }
    }

    private fun initView() {
        viewBinding.apply {
            dailyForecastList.adapter = adapter
        }
//        viewModel.getWeather(42.9832406, -81.243372)
    }

    companion object{
        const val REQUEST_KEY = "location"
        const val RESULT_KEY = "location_result_key"
    }
}