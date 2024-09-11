package com.example.weatherappassessment.core.presentation

sealed class LoadingState{
    data object Idle: LoadingState()
    data object Loading: LoadingState()
    data object Loaded: LoadingState()
    data object Error: LoadingState()
}