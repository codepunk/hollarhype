package com.codepunk.hollarhype.data.remote.interceptor

import android.content.Context
import android.net.ConnectivityManager
import com.codepunk.hollarhype.R
import com.codepunk.hollarhype.util.http.NoConnectivityException
import com.codepunk.hollarhype.util.isConnected
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkConnectionInterceptor @Inject constructor(
    @ApplicationContext private val context: Context,
    private val connectivityManager: ConnectivityManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!connectivityManager.isConnected()) {
            throw NoConnectivityException(
                message = context.getString(R.string.error_no_internet_try_again)
            )
        }
        return chain.proceed(chain.request().newBuilder().build())
    }
}
