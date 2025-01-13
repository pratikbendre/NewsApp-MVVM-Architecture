package com.pratikbendre.newsapp.ui.newsSources

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pratikbendre.newsapp.data.model.NewsSources
import com.pratikbendre.newsapp.databinding.NewsSourceItemLayoutBinding
import com.pratikbendre.newsapp.utils.ItemClickListener

class NewsSourcesAdapter(private val sourceList: ArrayList<NewsSources>) :
    RecyclerView.Adapter<NewsSourcesAdapter.DataViewHolder>() {
    lateinit var itemClickListener: ItemClickListener<String>

    class DataViewHolder(private val binding: NewsSourceItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsSources: NewsSources, itemClickListener: ItemClickListener<String>) {
            binding.sourceBtn.text = newsSources.name
            binding.sourceBtn.setOnClickListener {
                itemClickListener(newsSources.id!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            NewsSourceItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = sourceList.size
    override fun onBindViewHolder(holder: NewsSourcesAdapter.DataViewHolder, position: Int) =
        holder.bind(sourceList[position], itemClickListener)

    fun addData(list: List<NewsSources>) {
        sourceList.addAll(list)
    }

}