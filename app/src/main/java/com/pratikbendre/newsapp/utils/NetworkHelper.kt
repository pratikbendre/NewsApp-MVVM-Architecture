package com.pratikbendre.newsapp.utils

import android.content.Context
import android.net.ConnectivityManager

interface NetworkHelper {
    fun isNetworkConnected(): Boolean
}

class DefaultNetworkHelper constructor(private val context: Context) : NetworkHelper {
    override fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNW = cm.activeNetworkInfo
        return activeNW?.isConnected ?: false
    }
}