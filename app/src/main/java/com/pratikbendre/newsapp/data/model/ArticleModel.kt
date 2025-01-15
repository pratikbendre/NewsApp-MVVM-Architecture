package com.pratikbendre.newsapp.data.model

import com.google.gson.annotations.SerializedName
import com.pratikbendre.newsapp.data.local.entity.Article

data class ArticleModel(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("urlToImage")
    val imageurl: String = "",
    @SerializedName("source")
    val sourceModel: SourceModel
)


fun ArticleModel.toArticleEntity(): Article {
    return Article(
        title = title,
        description = description,
        url = url,
        imageUrl = imageurl,
        source = sourceModel.toSourceEntity()
    )
}