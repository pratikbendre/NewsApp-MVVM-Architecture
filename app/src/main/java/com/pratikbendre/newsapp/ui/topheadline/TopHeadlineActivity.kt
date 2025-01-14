package com.pratikbendre.newsapp.ui.topheadline

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.pratikbendre.newsapp.R
import com.pratikbendre.newsapp.data.model.Article
import com.pratikbendre.newsapp.data.model.Language
import com.pratikbendre.newsapp.databinding.ActivityTopHeadlineBinding
import com.pratikbendre.newsapp.databinding.FilterBottomsheetLayoutBinding
import com.pratikbendre.newsapp.ui.base.UiState
import com.pratikbendre.newsapp.ui.language.LanguageViewModel
import com.pratikbendre.newsapp.utils.showAlert
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TopHeadlineActivity : AppCompatActivity() {

    companion object {
        const val EXTRAS_COUNTRY = "EXTRAS_COUNTRY"
        fun getIntent(context: Context, country: String): Intent {
            return Intent(context, TopHeadlineActivity::class.java).apply {
                putExtra(EXTRAS_COUNTRY, country)
            }
        }
    }

    private lateinit var topHeadlineViewModel: TopHeadlineViewModel

    @Inject
    lateinit var adapter: TopHeadlineAdapter

    private lateinit var binding: ActivityTopHeadlineBinding

    private lateinit var languageViewModel: LanguageViewModel


    private var countryCode: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopHeadlineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupViewModel()
        setupObserver()

        val getBundle: Bundle? = intent.extras
        countryCode = getBundle!!.getString(EXTRAS_COUNTRY)
        topHeadlineViewModel.fetchNews(countryCode!!)
    }

    private fun setupUI() {
        var recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter

        adapter.itemClickListener = {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(it))
        }

        binding.filterFab.setOnClickListener {
            showFilters()
        }
    }

    private fun setupObserver() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                topHeadlineViewModel.uiState.collect {
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

    private fun renderList(articleList: List<Article>) {
        adapter.addData(articleList)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        topHeadlineViewModel = ViewModelProvider(this)[TopHeadlineViewModel::class.java]
        languageViewModel = ViewModelProvider(this)[LanguageViewModel::class.java]
    }


    private fun showFilters() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomsheetbinding = FilterBottomsheetLayoutBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(bottomsheetbinding.root)
        bottomSheetDialog.show()
        val chipGroup = bottomsheetbinding.chipGroup
        bottomsheetbinding.restBtn.setOnClickListener {
            for (i in 0 until chipGroup.childCount) {
                val chip = chipGroup.getChildAt(i) as? Chip
                chip?.isChecked = false
            }
        }
        bottomsheetbinding.applyBtn.setOnClickListener {
            val selectedChips = getSelectedChips(chipGroup)
            if (selectedChips.size == 2) {
                bottomSheetDialog.dismiss()
                topHeadlineViewModel.fetchNewsByLanguage(selectedChips)
            } else {
                Toast.makeText(
                    this@TopHeadlineActivity,
                    "Please select exactly 2 languages.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                languageViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            it.data.forEach {
                                chipGroup.addView(
                                    createTagChip(
                                        this@TopHeadlineActivity, it
                                    )
                                )
                            }
                        }

                        is UiState.Loading -> {
//                            binding.progressBar.visibility = View.VISIBLE
//                            binding.recyclerView.visibility = View.GONE
                        }

                        is UiState.Error -> {
//                            binding.progressBar.visibility = View.GONE
//                            binding.recyclerView.visibility = View.GONE

                        }
                    }
                }
            }
        }
    }

    private fun createTagChip(context: Context, language: Language): Chip {
        return Chip(context).apply {
            text = language.Name
            chipBackgroundColor =
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.chip_background))
            isCheckedIconVisible = true
            isCheckable = true
            setTextColor(ContextCompat.getColor(context, R.color.chip_text))
            chipBackgroundColor =
                ContextCompat.getColorStateList(context, R.color.chip_background_color)
            setTextColor(ContextCompat.getColorStateList(context, R.color.chip_text_color))
            tag = language.Code
        }
    }

    private fun getSelectedChips(chipGroup: ChipGroup): List<String> {
        return chipGroup.checkedChipIds.mapNotNull { id ->
            val chip = chipGroup.findViewById<Chip>(id)
            chip?.tag as? String
        }
    }


    private fun showError() {
        AlertDialog.Builder(this).apply {
            showAlert(this@TopHeadlineActivity,
                getString(R.string.oops),
                getString(R.string.something_went_wrong_lets_try_again_one_more_time),
                buttonClickListener = {
                    topHeadlineViewModel.fetchNews(countryCode!!)
                })
        }
    }
}