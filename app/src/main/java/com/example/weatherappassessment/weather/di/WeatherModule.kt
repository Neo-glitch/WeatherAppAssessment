package com.example.weatherappassessment.weather.di

import com.example.weatherappassessment.weather.data.datasources.local.LocalDataSourceImpl
import com.example.weatherappassessment.weather.data.datasources.remote.api.WeatherService
import com.example.weatherappassessment.weather.data.datasources.remote.RemoteDatasourceImpl
import com.example.weatherappassessment.weather.data.repository.RepositoryImpl
import com.example.weatherappassessment.weather.domain.datasource.LocalDataSource
import com.example.weatherappassessment.weather.domain.datasource.RemoteDataSource
import com.example.weatherappassessment.weather.domain.repository.Repository
import com.neocalc.neocalc.core.data.datasources.local.preferences.AppPreferences
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
    fun provideLocalDatasource(preferences: AppPreferences): LocalDataSource = LocalDataSourceImpl(preferences)

    @ViewModelScoped
    @Provides
    fun provideWeatherRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource) : Repository = RepositoryImpl(remoteDataSource, localDataSource)
}