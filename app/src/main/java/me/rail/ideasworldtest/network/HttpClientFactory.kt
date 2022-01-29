package me.rail.ideasworldtest.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpClientFactory(
    private val url: String,
    private val connectTimeout: Long,
    private val readTimeout: Long
) {

    fun create(): HttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
            .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(SetHeadersInterceptor())
            .retryOnConnectionFailure(true)

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .baseUrl(url)
            .build()
            .create(HttpClient::class.java)
    }
}