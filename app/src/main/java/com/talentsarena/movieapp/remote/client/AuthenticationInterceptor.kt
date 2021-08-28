package com.talentsarena.movieapp.remote.client

import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Authentication interceptor class that intercepts every retrofit request to add [apiKey].
 */
class AuthenticationInterceptor(private val apiKey: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl = original.url
        val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", apiKey).build()
        val requestBuilder: Request.Builder = original.newBuilder().url(url)
        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}