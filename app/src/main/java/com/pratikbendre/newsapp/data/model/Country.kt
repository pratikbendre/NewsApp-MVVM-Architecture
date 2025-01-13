package com.pratikbendre.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val countryName: String = "",
    @SerializedName("code")
    val countryCode: String = "",
)