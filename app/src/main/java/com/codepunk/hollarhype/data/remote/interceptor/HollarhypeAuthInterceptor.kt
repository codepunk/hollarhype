package com.codepunk.hollarhype.data.remote.interceptor

import com.codepunk.hollarhype.domain.model.Authenticated
import com.codepunk.hollarhype.manager.UserSessionManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HollarhypeAuthInterceptor @Inject constructor(
    private val userSessionManager: UserSessionManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request()
            .newBuilder()
            .apply {
                val authentication = userSessionManager.userSession.value
                if (authentication is Authenticated) {
                    addHeader(AUTHORIZATION, authentication.authToken)
                }
            }
            .build()
        return chain.proceed(newRequest)
    }

    companion object {
        private const val AUTHORIZATION: String = "Authorization"
    }
}