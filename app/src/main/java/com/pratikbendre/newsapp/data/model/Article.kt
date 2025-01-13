package com.pratikbendre.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("urlToImage")
    val imageurl: String = "",
    @SerializedName("source")
    val source: Source
)