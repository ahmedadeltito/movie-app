package com.talentsarena.datasource.remote.di

import com.talentsarena.datasource.remote.client.RetrofitClient
import retrofit2.Retrofit

/**
 * Service locator to generate [Retrofit] client.
 */
object RemoteServiceLocator {

    fun getRetrofit(apiKey: String): Retrofit {
        return RetrofitClient(apiKey = apiKey).provideRetrofit()
    }
}