package com.example.trendyol_internship.data.detail.model

import com.google.gson.annotations.SerializedName

class Publisher(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("games_count")
    val games_count: Int?,
    @SerializedName("image_background")
    val image_background: String?,
)