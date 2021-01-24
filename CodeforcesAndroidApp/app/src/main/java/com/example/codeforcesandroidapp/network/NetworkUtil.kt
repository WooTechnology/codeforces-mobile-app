package com.example.codeforcesandroidapp.network

import com.example.codeforcesandroidapp.network.services.ApiService
import com.example.codeforcesandroidapp.utils.Constants.CODEFORCES_BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkUtil {

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    fun createRetrofitClient(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(CODEFORCES_BASE_URL)
        .client(okHttpClient())
        .build()

    fun createCodeforcesService(client: Retrofit): ApiService = client.create(ApiService::class.java)


    private fun okHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(60, TimeUnit.SECONDS)
        httpClient.connectTimeout(60, TimeUnit.SECONDS)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.interceptors().add(interceptor)
        return httpClient.build()
    }

}