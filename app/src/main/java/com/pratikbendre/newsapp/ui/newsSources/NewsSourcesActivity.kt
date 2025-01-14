package com.pratikbendre.newsapp.ui.newsSources

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
import com.pratikbendre.newsapp.data.model.NewsSources
import com.pratikbendre.newsapp.databinding.ActivityNewsSourcesBinding
import com.pratikbendre.newsapp.ui.base.UiState
import com.pratikbendre.newsapp.ui.topheadlinebysource.TopHeadlineBySourceActivity
import com.pratikbendre.newsapp.utils.AppConstants.SOURCE
import com.pratikbendre.newsapp.utils.showAlert
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class NewsSourcesActivity : AppCompatActivity() {
    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, NewsSourcesActivity::class.java)
        }
    }

    private lateinit var binding: ActivityNewsSourcesBinding

    private lateinit var newsSourcesViewModel: NewsSourcesViewModel

    @Inject
    lateinit var adapter: NewsSourcesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsSourcesBinding.inflate(layoutInflater)
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
            startActivity(TopHeadlineBySourceActivity.getIntent(this, SOURCE, it))
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsSourcesViewModel.uiState.collect {
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

    private fun renderList(newssources: List<NewsSources>) {
        adapter.addData(newssources)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        newsSourcesViewModel = ViewModelProvider(this)[NewsSourcesViewModel::class.java]
    }


    private fun showError() {
        AlertDialog.Builder(this).apply {
            showAlert(
                this@NewsSourcesActivity,
                getString(R.string.oops),
                getString(R.string.something_went_wrong_lets_try_again_one_more_time),
                buttonClickListener = {
                    newsSourcesViewModel.fetchSource()
                }
            )
        }
    }
}