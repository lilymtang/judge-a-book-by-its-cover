package com.example.judgeabookbyitscover.model

import com.example.judgeabookbyitscover.model.datamodels.Volume
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit service that maps the different endpoints on the API, you'd create one
 * method per endpoint, and use the @Path, @Query and other annotations to customize
 * these at runtime
 * */
interface GoogleBookApi {
    @GET("/books/v1/volumes/{id}")
    fun getVolume(@Path("id") id: String): Call<Volume>
}