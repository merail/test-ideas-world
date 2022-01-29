package me.rail.ideasworldtest.network

import okhttp3.Interceptor
import okhttp3.Response

class SetHeadersInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder().apply {
            header("Accept-Version", "v1")
            header("Authorization", "Client-ID HfnkiuBVC0nfxPiwjvOJ2VB289uV5Jjp-IirikOGTP8")
        }

        return chain.proceed(builder.build())
    }
}