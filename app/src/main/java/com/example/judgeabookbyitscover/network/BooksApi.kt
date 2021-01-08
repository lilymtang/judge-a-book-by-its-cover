package com.example.judgeabookbyitscover.network

import com.example.judgeabookbyitscover.BuildConfig
import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.nytimes.com"
private const val API_KEY =

/**
 * Use the Retrofit builder to build a retrofit object using Moshi as a JSON converter with the base URL set here.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api-key", API_KEY)
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }
            .build()
    )
    .build()

/**
 * A public Api object that exposes the lazy-initialized Retrofit service.
 */
object BooksApi {
    val BooksApiService : NytApi by lazy {retrofit.create(NytApi::class.java)}
}