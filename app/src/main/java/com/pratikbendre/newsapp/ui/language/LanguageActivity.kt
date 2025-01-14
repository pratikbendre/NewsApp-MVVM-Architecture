package com.pratikbendre.newsapp.ui.language

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
import com.pratikbendre.newsapp.data.model.Language
import com.pratikbendre.newsapp.databinding.ActivityLanguageBinding
import com.pratikbendre.newsapp.ui.base.UiState
import com.pratikbendre.newsapp.ui.topheadlinebysource.TopHeadlineBySourceActivity
import com.pratikbendre.newsapp.utils.AppConstants.LANGUAGE
import com.pratikbendre.newsapp.utils.showAlert
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LanguageActivity : AppCompatActivity() {
    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, LanguageActivity::class.java)
        }
    }

    private lateinit var binding: ActivityLanguageBinding


    private lateinit var languageViewModel: LanguageViewModel


    @Inject
    lateinit var adapter: LanguageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupUI()
        setupObservers()
        adapter.itemClickListener = {
            startActivity(TopHeadlineBySourceActivity.getIntent(this, LANGUAGE, it))
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
                languageViewModel.uiState.collect {
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

    private fun setupViewModel() {
        languageViewModel = ViewModelProvider(this)[LanguageViewModel::class.java]
    }


    private fun renderList(languagesList: List<Language>) {
        adapter.addData(languagesList)
        adapter.notifyDataSetChanged()
    }


    private fun showError() {
        AlertDialog.Builder(this).apply {
            showAlert(
                this@LanguageActivity,
                getString(R.string.oops),
                getString(R.string.something_went_wrong_lets_try_again_one_more_time),
                buttonClickListener = {
                    languageViewModel.fetchLanguages()
                }
            )
        }
    }
}