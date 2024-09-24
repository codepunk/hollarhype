package com.codepunk.hollarhype.util

import android.net.ConnectivityManager

fun ConnectivityManager.isConnected(): Boolean = getNetworkCapabilities(
    activeNetwork
)?.hasCapability(
    android.net.NetworkCapabilities.NET_CAPABILITY_VALIDATED
) == true
