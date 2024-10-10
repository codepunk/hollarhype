package com.codepunk.hollarhype.data.remote.interceptor

import android.net.ConnectivityManager
import com.codepunk.hollarhype.util.http.NoConnectivityException
import com.codepunk.hollarhype.util.isConnected
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkConnectionInterceptor @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!connectivityManager.isConnected()) {
            throw NoConnectivityException(
                message = "Not connected to Internet"
            )
        }
        return chain.proceed(chain.request().newBuilder().build())
    }
}
