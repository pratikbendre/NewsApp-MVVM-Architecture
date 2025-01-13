package com.pratikbendre.newsapp.ui.topheadlinebysource

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pratikbendre.newsapp.NewsApplication
import com.pratikbendre.newsapp.data.model.Article
import com.pratikbendre.newsapp.databinding.ActivityTopHeadlineBySourceBinding
import com.pratikbendre.newsapp.di.components.DaggerActivityComponent
import com.pratikbendre.newsapp.di.module.ActivityModule
import com.pratikbendre.newsapp.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopHeadlineBySourceActivity : AppCompatActivity() {
    companion object {
        const val EXTRAS_SOURCE = "EXTRAS_SOURCE"
        fun getIntent(context: Context, source: String): Intent {
            return Intent(context, TopHeadlineBySourceActivity::class.java)
                .apply {
                    putExtra(EXTRAS_SOURCE, source)
                }
        }
    }

    private lateinit var binding: ActivityTopHeadlineBySourceBinding

    @Inject
    lateinit var adapter: TopHeadlineBySourceAdapter

    @Inject
    lateinit var topHeadlineBySourceViewModel: TopHeadlineBySourceViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        getDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityTopHeadlineBySourceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObservers()
        val bundle: Bundle? = intent.extras
        val source: String? = bundle?.getString(EXTRAS_SOURCE)
        topHeadlineBySourceViewModel.fetchNews(source!!)
    }

    private fun setupUI() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                topHeadlineBySourceViewModel.uiState.collect {
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
                            binding.recyclerView.visibility = View.GONE
                            Toast.makeText(
                                this@TopHeadlineBySourceActivity,
                                it.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun getDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }

    private fun renderList(article: List<Article>) {
        adapter.addData(article)
        adapter.notifyDataSetChanged()
    }
}