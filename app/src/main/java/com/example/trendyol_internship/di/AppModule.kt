package com.example.trendyol_internship.di

import com.example.trendyol_internship.data.network.NetworkService
import com.example.trendyol_internship.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // ?
object AppModule {

    @Provides
    @Singleton
    fun getRetrofitInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Gson converts json into kotlin object
            .build()

    @Provides
    @Singleton
    fun provideListingAPI(retrofit: Retrofit): NetworkService =
        retrofit.create(NetworkService::class.java)

}