package com.example.weatherappassessment.weather.di

import com.example.weatherappassessment.weather.data.remote.api.WeatherService
import com.example.weatherappassessment.weather.data.remote.datasource.RemoteDatasourceImpl
import com.example.weatherappassessment.weather.data.remote.datasource.RepositoryImpl
import com.example.weatherappassessment.weather.domain.datasource.RemoteDataSource
import com.example.weatherappassessment.weather.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit


@Module
@InstallIn(ViewModelComponent::class)
class WeatherModule {

    @ViewModelScoped
    @Provides
    fun provideWeatherService(retrofit: Retrofit) : WeatherService = retrofit.create(WeatherService::class.java)

    @ViewModelScoped
    @Provides
    fun provideRemoteDatasource(service: WeatherService): RemoteDataSource = RemoteDatasourceImpl(service)

    @ViewModelScoped
    @Provides
    fun provideWeatherRepository(remoteDataSource: RemoteDataSource) : Repository = RepositoryImpl(remoteDataSource)
}