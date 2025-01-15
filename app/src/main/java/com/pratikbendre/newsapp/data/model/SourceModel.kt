package com.pratikbendre.newsapp.data.model

import com.google.gson.annotations.SerializedName
import com.pratikbendre.newsapp.data.local.entity.Source

data class SourceModel(
    @SerializedName("id") val id: String = "",
    @SerializedName("name") val name: String = "",
)


fun SourceModel.toSourceEntity(): Source {
    return Source(id, name)
}