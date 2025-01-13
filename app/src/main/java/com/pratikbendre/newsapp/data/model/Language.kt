package com.pratikbendre.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class Language(
    @SerializedName("name")
    val Name: String = "",
    @SerializedName("code")
    val Code: String = "",
)