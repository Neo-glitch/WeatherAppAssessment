package com.example.weatherappassessment.weather.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weatherappassessment.core.presentation.LoadingState
import com.example.weatherappassessment.core.util.LifeCycleUtil.collectInLifecycleScope
import com.example.weatherappassessment.core.util.addFlowTextWatcher
import com.example.weatherappassessment.core.util.hide
import com.example.weatherappassessment.core.util.show
import com.example.weatherappassessment.core.util.showToast
import com.example.weatherappassessment.core.util.visible
import com.example.weatherappassessment.databinding.FragmentSearchBinding
import com.example.weatherappassessment.weather.data.entity.Location
import com.example.weatherappassessment.weather.presentation.uiState.SearchUiState
import com.example.weatherappassessment.weather.presentation.SearchViewModel
import com.example.weatherappassessment.weather.presentation.adapter.LocationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchCityFragment: Fragment() {

    private val viewModel by viewModels<SearchViewModel>()

    private val viewBinding by lazy { FragmentSearchBinding.inflate(layoutInflater) }
    private lateinit var adapter: LocationAdapter

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
            handleState(uiState)
        }
    }

    private fun handleState(uiState: SearchUiState) {
        when (uiState.loadingState) {
            LoadingState.Error -> handleErrorState()
            LoadingState.Idle -> handleIdleState()
            LoadingState.Loaded -> handleLoadedState(uiState)
            LoadingState.Loading -> handleLoadingState()
        }
    }

    private fun handleIdleState() {
        viewBinding.apply {
            searchResultList.hide()
            progressLayout.root.hide()
            noResultsLabel.hide()
        }
    }

    private fun handleLoadingState() {
        viewBinding.apply {
            progressLayout.root.show()
            searchResultList.hide()
            noResultsLabel.hide()
        }
    }

    private fun handleErrorState() {
        viewBinding.apply {
            progressLayout.root.hide()
            searchResultList.hide()
            noResultsLabel.hide()
        }

    }

    private fun handleLoadedState(uiState: SearchUiState) {
        viewBinding.apply {
            progressLayout.root.hide()
            searchResultList.show()
            noResultsLabel.hide()
            adapter.submitList(uiState.locations?.toMutableList())
        }
    }

    private fun initView() {
        viewBinding.apply {
            adapter = LocationAdapter { popFragmentWithResult(it) }
            searchResultList.adapter = adapter
            backBtn.setOnClickListener { findNavController().navigateUp() }

            lifecycleScope.launch {
                searchInput.addFlowTextWatcher()
                    .debounce(400L)
                    .filter { it.isNotEmpty() }
                    .distinctUntilChanged()
                    .collect { text ->
                        viewModel.getCities(text)
                    }
            }
        }
    }

    private fun popFragmentWithResult(location: Location){
        setFragmentResult(
            requestKey = HomeFragment.REQUEST_KEY,
            result = bundleOf(HomeFragment.RESULT_KEY to location)
        )
        findNavController().navigateUp()
    }
}