package com.pratikbendre.newsapp.ui.topheadlinebysource

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.pratikbendre.newsapp.NewsApplication
import com.pratikbendre.newsapp.R
import com.pratikbendre.newsapp.data.model.Article
import com.pratikbendre.newsapp.databinding.ActivityTopHeadlineBySourceBinding
import com.pratikbendre.newsapp.di.components.DaggerActivityComponent
import com.pratikbendre.newsapp.di.module.ActivityModule
import com.pratikbendre.newsapp.ui.base.UiState
import com.pratikbendre.newsapp.utils.AppConstants.LANGUAGE
import com.pratikbendre.newsapp.utils.AppConstants.SOURCE
import com.pratikbendre.newsapp.utils.showAlert
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopHeadlineBySourceActivity : AppCompatActivity() {
    companion object {
        const val EXTRAS_FILTER_NAME = "EXTRAS_FILTER_NAME"
        const val EXTRAS_FILTER_VALUE = "EXTRAS_FILTER_VALUE"

        fun getIntent(context: Context, filterName: String, filterValue: String): Intent {
            return Intent(context, TopHeadlineBySourceActivity::class.java).apply {
                putExtra(EXTRAS_FILTER_NAME, filterName)
                putExtra(EXTRAS_FILTER_VALUE, filterValue)
            }
        }
    }

    private lateinit var binding: ActivityTopHeadlineBySourceBinding

    @Inject
    lateinit var adapter: TopHeadlineBySourceAdapter

    @Inject
    lateinit var topHeadlineBySourceViewModel: TopHeadlineBySourceViewModel

    private lateinit var filterName: String
    private lateinit var filterValue: String
    override fun onCreate(savedInstanceState: Bundle?) {
        getDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityTopHeadlineBySourceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObservers()
        val bundle: Bundle? = intent.extras
        filterName = bundle?.getString(EXTRAS_FILTER_NAME)!!
        filterValue = bundle?.getString(EXTRAS_FILTER_VALUE)!!

        if (filterName.equals(SOURCE, true)) {
            topHeadlineBySourceViewModel.fetchNewsBySource(filterValue!!)
        } else if (filterName.equals(LANGUAGE, true)) {
            topHeadlineBySourceViewModel.fetchNewsByLanguage(filterValue!!)
        }
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
                            showError()
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

    private fun renderList(article: List<Article>) {
        adapter.addData(article)
        adapter.notifyDataSetChanged()
    }


    private fun showError() {
        AlertDialog.Builder(this).apply {
            showAlert(
                this@TopHeadlineBySourceActivity,
                getString(R.string.oops),
                getString(R.string.something_went_wrong_lets_try_again_one_more_time),
                buttonClickListener = {
                    if (filterName.equals(SOURCE, true)) {
                        topHeadlineBySourceViewModel.fetchNewsBySource(filterValue!!)
                    } else if (filterName.equals(LANGUAGE, true)) {
                        topHeadlineBySourceViewModel.fetchNewsByLanguage(filterValue!!)
                    }
                }
            )
        }
    }
}