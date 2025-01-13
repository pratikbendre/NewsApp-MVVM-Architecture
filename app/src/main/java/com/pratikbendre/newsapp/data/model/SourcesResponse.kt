package com.pratikbendre.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class SourcesResponse(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("sources")
    val source: List<NewsSources> = ArrayList(),
)