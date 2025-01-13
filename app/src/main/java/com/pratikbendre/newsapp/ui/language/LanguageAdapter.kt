package com.pratikbendre.newsapp.ui.language

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pratikbendre.newsapp.data.model.Language
import com.pratikbendre.newsapp.databinding.NewsSourceItemLayoutBinding
import com.pratikbendre.newsapp.utils.ItemClickListener

class LanguageAdapter(private val languageList: ArrayList<Language>) :
    RecyclerView.Adapter<LanguageAdapter.DataViewHolder>() {
    lateinit var itemClickListener: ItemClickListener<String>

    class DataViewHolder(private val binding: NewsSourceItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(language: Language, itemClickListener: ItemClickListener<String>) {
            binding.sourceBtn.text = language.Name
            binding.sourceBtn.setOnClickListener {
                itemClickListener(language.Code)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        NewsSourceItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = languageList.size
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(languageList[position], itemClickListener)

    fun addData(list: List<Language>) {
        languageList.clear()
        languageList.addAll(list)
    }
}