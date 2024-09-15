package com.codepunk.hollarhype.data.remote.interceptor

import android.os.Build.VERSION.RELEASE
import com.codepunk.hollarhype.BuildConfig.APPLICATION_NAME
import com.codepunk.hollarhype.BuildConfig.VERSION_CODE
import com.codepunk.hollarhype.BuildConfig.VERSION_NAME
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HollarhypeUserAgentInterceptor @Inject constructor() : Interceptor {

    // region Methods

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request()
            .newBuilder()
            .header(
                name = HEADER_USER_AGENT,
                value = "$APPLICATION_NAME/$VERSION_CODE ($VERSION_NAME; Android $RELEASE) OkHttp"
            )
            .build()
        return chain.proceed(newRequest)
    }

    // region Methods

    // region Companion object

    companion object {

        // region Variables

        private const val HEADER_USER_AGENT = "User-Agent"

        // endregion Variables

    }

    // endregion Companion object

}