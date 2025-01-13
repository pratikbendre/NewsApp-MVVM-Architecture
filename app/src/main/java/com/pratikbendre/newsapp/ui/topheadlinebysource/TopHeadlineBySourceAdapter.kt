package com.pratikbendre.newsapp.ui.topheadlinebysource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pratikbendre.newsapp.data.model.Article
import com.pratikbendre.newsapp.databinding.TopHeadlineItemLayoutBinding

class TopHeadlineBySourceAdapter(private val articleList: ArrayList<Article>) :
    RecyclerView.Adapter<TopHeadlineBySourceAdapter.DataViewholder>() {
    class DataViewholder(private val binding: TopHeadlineItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description
            binding.textViewSource.text = article.source.name
            Glide.with(binding.imageViewBanner.context)
                .load(article.imageurl)
                .into(binding.imageViewBanner)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewholder(
        TopHeadlineItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(
        holder: DataViewholder, position: Int
    ) = holder.bind(articleList[position])

    override fun getItemCount(): Int = articleList.size
    fun addData(list: List<Article>) {
        articleList.clear()
        articleList.addAll(list)
    }
}