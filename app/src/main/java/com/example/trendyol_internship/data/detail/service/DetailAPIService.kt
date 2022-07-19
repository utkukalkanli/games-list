package com.example.trendyol_internship.data.detail.service

import com.example.trendyol_internship.data.detail.model.GameDetail

import com.example.trendyol_internship.util.Constants
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class DetailAPIService {

    fun getData(gameID: Int): Single<GameDetail> {
        val BASE_URL = Constants.BASE_URL
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(DetailAPI::class.java)
        println(gameID.toString() + Constants.API_KEY)
        return api.getGameDetail(gameID.toString() + Constants.API_KEY)
    }
}