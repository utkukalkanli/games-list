package com.example.trendyol_internship.data.listing.service

import com.example.trendyol_internship.data.listing.model.Game
import io.reactivex.Single
import retrofit2.http.GET
interface ListAPI {
    // call retrofitin indirme methodu, single ise rxjava sınıfı, call ve observable gibi içerisine type yazarak kullanılıyor
    @GET("utkukalkanli319/json/main/games.json?token=GHSAT0AAAAAABWCDAS2RKOK3UAXTDSYLCCEYWHFFDA")
    fun getGamesList():Single<List<Game>>
}