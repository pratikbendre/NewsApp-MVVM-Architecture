package com.pratikbendre.newsapp.ui.search

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pratikbendre.newsapp.NewsApplication
import com.pratikbendre.newsapp.R
import com.pratikbendre.newsapp.data.model.Article
import com.pratikbendre.newsapp.databinding.ActivitySearchBinding
import com.pratikbendre.newsapp.di.components.DaggerActivityComponent
import com.pratikbendre.newsapp.di.module.ActivityModule
import com.pratikbendre.newsapp.ui.base.UiState
import com.pratikbendre.newsapp.utils.getQueryTextChangeStateFlow
import com.pratikbendre.newsapp.utils.showAlert
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {
    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }

    lateinit var binding: ActivitySearchBinding

    @Inject
    lateinit var adapter: SearchAdapter

    @Inject
    lateinit var searchViewModel: SearchViewModel

    private var search_query: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDependencies()
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()
        setupSearchflow()
        adapter.itemClickListener = {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(it))
        }
    }

    private fun setupSearchflow() {
        lifecycleScope.launch {
            binding.searchView.getQueryTextChangeStateFlow()
                .collect { query ->
                    search_query = query
                    searchViewModel.fetchnews(query)
                }
        }
    }

    private fun setupUI() {
        var recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderlist(it.data)
                            binding.recyclerView.visibility = View.VISIBLE
                        }

                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.recyclerView.visibility = View.GONE
                            showError()
                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun getDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }

    fun renderlist(list: List<Article>) {
        adapter.addData(list)
        adapter.notifyDataSetChanged()
    }


    private fun showError() {
        AlertDialog.Builder(this).apply {
            showAlert(
                this@SearchActivity,
                getString(R.string.oops),
                getString(R.string.something_went_wrong_lets_try_again_one_more_time),
                buttonClickListener = {
                    searchViewModel.fetchnews(search_query!!)
                }
            )
        }
    }
}