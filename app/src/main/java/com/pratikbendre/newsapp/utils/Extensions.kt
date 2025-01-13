package com.pratikbendre.newsapp.utils

import android.app.AlertDialog
import android.content.Context
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.SearchView
import com.bumptech.glide.Glide
import com.pratikbendre.newsapp.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {
    val query = MutableStateFlow("")
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }
    })
    return query
}

fun AlertDialog.Builder.showAlert(
    context: Context,
    title: String,
    description: String,
    buttonClickListener: ButtonClickListener
) {
    val builder = this.create()
    val view = builder.layoutInflater.inflate(R.layout.error_alert_layout, null)
    builder.setView(view)
    val titleTv = view.findViewById<TextView>(R.id.title_tv)
    val descriptionTv = view.findViewById<TextView>(R.id.description_tv)
    val tryAgainBtn = view.findViewById<Button>(R.id.try_again_btn)
    titleTv.text = title
    descriptionTv.text = description
    builder.setCanceledOnTouchOutside(false)
    tryAgainBtn.setOnClickListener {
        buttonClickListener()
        builder.dismiss()
    }

    builder.show()
}


fun AppCompatImageView.loadimage(url: String) {
    Glide.with(this).load(url).into(this)
}

