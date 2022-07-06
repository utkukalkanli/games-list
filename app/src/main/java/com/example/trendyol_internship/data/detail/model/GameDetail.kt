package com.example.trendyol_internship.data.detail.model


import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

// release date
// genres - list
// playtime
// publishers
// reddit url
// website url
data class GameDetail(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("metacritic")
    val metaCritic: Int?,

    @SerializedName("background_image")
    val backgroundImage: String?,

    @SerializedName("description_raw")
    val descriptionRaw: String?,

    @SerializedName("released")
    val releaseDate: Date?,

    @SerializedName("genres")
    val genres: ArrayList<String>?,

    @SerializedName("playtime")
    val playTime: Int?,

    @SerializedName("publishers")
    val publishers: ArrayList<String>?,

    @SerializedName("reddit_url")
    val redditURL: String?,

    @SerializedName("website")
    val websiteURL: String?

)
