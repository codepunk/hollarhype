package com.codepunk.hollarhype.util

import android.net.ConnectivityManager
import com.codepunk.hollarhype.util.http.NoConnectivityException

fun ConnectivityManager.isConnected(): Boolean = getNetworkCapabilities(
    activeNetwork
)?.hasCapability(
    android.net.NetworkCapabilities.NET_CAPABILITY_VALIDATED
) == true

fun ConnectivityManager.checkConnectivity(): Boolean = if (isConnected()) {
    true
} else {
    throw NoConnectivityException(message = "No Internet connection")
}
