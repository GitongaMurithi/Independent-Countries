package com.example.countries.di

import com.example.countries.common.Constants
import com.example.countries.data.CountriesApi
import com.example.countries.data.repository.CountryRepositoryImplementation
import com.example.countries.domain.model.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun providesCountryApi(): CountriesApi {
//        val okHttpClient = OkHttpClient.Builder()
//            .connectTimeout(15, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .build()

    return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
//        .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRepository(api: CountriesApi) : CountryRepository = CountryRepositoryImplementation(api)
}