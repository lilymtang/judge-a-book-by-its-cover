package com.example.judgeabookbyitscover.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit service that maps the different endpoints on the API, you'd create one
 * method per endpoint, and use the @Path, @Query and other annotations to customize
 * these at runtime
 * */
interface GoogleBookApi {
    @GET("/books/v1/volumes/{id}")
    fun getVolume(@Path("id") id: String): Call<Volume>
}