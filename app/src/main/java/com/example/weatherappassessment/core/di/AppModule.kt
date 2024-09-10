package com.example.weatherappassessment.core.di

import com.example.weatherappassessment.BuildConfig
import com.example.weatherappassessment.core.data.util.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @LoggerInterceptor loggingInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
//            .addInterceptor(connectionInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @LoggerInterceptor
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): Interceptor {
        val logger = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            logger.level = HttpLoggingInterceptor.Level.BODY
        else
            logger.level = HttpLoggingInterceptor.Level.NONE

        return logger
    }

//    @ConnectionInterceptor
//    @Singleton
//    @Provides
//    fun provideNetworkConnectionInterceptor() : Interceptor{
//        return NetworkConnectionInterceptor()
//    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggerInterceptor