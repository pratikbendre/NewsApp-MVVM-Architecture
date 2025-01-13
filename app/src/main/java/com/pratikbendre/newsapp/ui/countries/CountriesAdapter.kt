package com.pratikbendre.newsapp.ui.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pratikbendre.newsapp.data.model.Country
import com.pratikbendre.newsapp.databinding.NewsSourceItemLayoutBinding
import com.pratikbendre.newsapp.utils.ItemClickListener

class CountriesAdapter(private val countriesList: ArrayList<Country>) :
    RecyclerView.Adapter<CountriesAdapter.DataViewholder>() {
    lateinit var itemClickListener: ItemClickListener<String>

    class DataViewholder(private val binding: NewsSourceItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country, itemClickListener: ItemClickListener<String>) {
            binding.sourceBtn.text = country.countryName
            binding.sourceBtn.setOnClickListener {
                itemClickListener(country.countryCode)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = DataViewholder(
        NewsSourceItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: DataViewholder, position: Int) =
        holder.bind(countriesList[position], itemClickListener)

    override fun getItemCount(): Int = countriesList.size
    fun addData(list: List<Country>) {
        countriesList.clear()
        countriesList.addAll(list)
    }
}