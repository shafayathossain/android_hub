package com.example.bs217.androidhub.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object MyUtil{

    fun isNetworkAvailable(context: Context?): Boolean {
        val connectivity = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity == null) {
            return false
        }
        // get network info for all of the data interfaces (e.g. WiFi, 3G, LTE, etc.)
        val info = connectivity.activeNetworkInfo
        // make sure that there is at least one interface to test against
        if (info != null) {
            // iterate through the interfaces
                if (info.state == NetworkInfo.State.CONNECTED) {
                    return true
                }

        }
        return false
    }
}