package com.pratikbendre.newsapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pratikbendre.newsapp.databinding.ActivityMainBinding
import com.pratikbendre.newsapp.ui.newsSources.NewsSourcesActivity
import com.pratikbendre.newsapp.ui.topheadline.TopHeadlineActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        binding.topHeadlinesBtn.setOnClickListener {
            startActivity(TopHeadlineActivity.getIntent(this))
        }
        binding.newsSourcesBtn.setOnClickListener {
            startActivity(NewsSourcesActivity.getIntent(this))
        }
    }
}