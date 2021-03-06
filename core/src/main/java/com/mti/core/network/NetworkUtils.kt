package com.mti.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * network utils
 * provide action network data
 * is available connection or not
 */
class NetworkUtil {
    @Suppress("DEPRECATION")
    fun isNetworkAvailable(context: Context): Boolean {
        var available = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> available = true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> available = true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> available = true
                }
            }
        } else {
            val activeNetwork = connectivityManager.activeNetworkInfo
            available = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
        return available
    }
    companion object {
        private var networkHelper: NetworkUtil? = null
        @get:Synchronized
        val instance: NetworkUtil?
            get() {
                if (networkHelper == null) {
                    networkHelper = NetworkUtil()
                }
                return networkHelper
            }
    }
}