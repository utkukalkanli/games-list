package com.example.trendyol_internship.data.detail.service

import com.example.trendyol_internship.data.detail.model.GameDetail
import com.example.trendyol_internship.data.listing.model.Game
import io.reactivex.Single
import retrofit2.http.GET

interface DetailAPI {
    // call retrofitin indirme methodu, single ise rxjava sınıfı, call ve observable gibi içerisine type yazarak kullanılıyor
    @GET("utkukalkanli319/json/blob/main/single.json")
    fun getGameDetail(): Single<GameDetail>
}