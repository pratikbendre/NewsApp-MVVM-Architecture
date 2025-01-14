package com.pratikbendre.newsapp.ui.countries

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pratikbendre.newsapp.R
import com.pratikbendre.newsapp.data.model.Country
import com.pratikbendre.newsapp.databinding.ActivityCountriesBinding
import com.pratikbendre.newsapp.ui.base.UiState
import com.pratikbendre.newsapp.ui.topheadline.TopHeadlineActivity
import com.pratikbendre.newsapp.utils.showAlert
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CountriesActivity : AppCompatActivity() {
    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, CountriesActivity::class.java)
        }
    }

    lateinit var binding: ActivityCountriesBinding

    @Inject
    lateinit var adapter: CountriesAdapter


    private lateinit var countriesViewModel: CountriesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.itemClickListener = {
            startActivity(TopHeadlineActivity.getIntent(this, it))
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                countriesViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                            binding.recyclerView.visibility = View.VISIBLE
                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }

                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            showError()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(list: List<Country>) {
        adapter.addData(list)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        countriesViewModel = ViewModelProvider(this)[CountriesViewModel::class.java]
    }


    private fun showError() {
        AlertDialog.Builder(this).apply {
            showAlert(
                this@CountriesActivity,
                getString(R.string.oops),
                getString(R.string.something_went_wrong_lets_try_again_one_more_time),
                buttonClickListener = {
                    countriesViewModel.fetchCountries()
                }
            )
        }
    }
}