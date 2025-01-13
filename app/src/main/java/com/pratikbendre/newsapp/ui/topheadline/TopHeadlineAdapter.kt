package com.pratikbendre.newsapp.ui.topheadline

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pratikbendre.newsapp.data.model.Article
import com.pratikbendre.newsapp.databinding.TopHeadlineItemLayoutBinding
import com.pratikbendre.newsapp.utils.ItemClickListener
import com.pratikbendre.newsapp.utils.loadimage

class TopHeadlineAdapter(private val articleList: ArrayList<Article>) :
    RecyclerView.Adapter<TopHeadlineAdapter.DataViewHolder>() {
    lateinit var itemClickListener: ItemClickListener<String>

    class DataViewHolder(private val binding: TopHeadlineItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article, itemClickListener: ItemClickListener<String>) {
            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description
            binding.textViewSource.text = article.source.name
            binding.imageViewBanner.loadimage(article.imageurl)
            itemView.setOnClickListener {
                itemClickListener(article.url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            TopHeadlineItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = articleList.size
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(articleList[position], itemClickListener)

    fun addData(list: List<Article>) {
        articleList.clear()
        articleList.addAll(list)
    }

}