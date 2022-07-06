package com.example.trendyol_internship.data.listing.service

import com.example.trendyol_internship.data.listing.model.Game
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ListingAPIService {
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ListAPI::class.java)

    fun getList(): Single<List<Game>>{
        return api.getGamesList()
    }
}