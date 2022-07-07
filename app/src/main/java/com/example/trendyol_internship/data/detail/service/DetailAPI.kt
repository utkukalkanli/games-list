package com.example.trendyol_internship.data.detail.service

import com.example.trendyol_internship.data.detail.model.GameDetail
import com.example.trendyol_internship.data.listing.model.Game
import com.example.trendyol_internship.util.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailAPI {

    // call retrofitin indirme methodu, single ise rxjava sınıfı, call ve observable gibi içerisine type yazarak kullanılıyor
    @GET("games/{id}")
    fun getGameDetail(@Path("id") id : String):Single<GameDetail>
}