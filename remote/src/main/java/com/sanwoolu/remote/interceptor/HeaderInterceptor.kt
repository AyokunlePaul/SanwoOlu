package com.sanwoolu.remote.interceptor

import com.sanwoolu.remote.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("app-id", BuildConfig.APP_ID)
        return chain.proceed(builder.build())
    }
}