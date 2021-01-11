package com.example.judgeabookbyitscover.model

import com.example.judgeabookbyitscover.model.datamodels.ListsOverview
import retrofit2.Call
import retrofit2.http.GET

/**
 * Retrofit service that maps the different endpoints on the API, you'd create one
 * method per endpoint, and use the @Path, @Query and other annotations to customize
 * these at runtime
 * */
interface NytApi {
    @GET("/svc/books/v3/lists/overview.json")
    fun getListsOverview(): Call<ListsOverview>
}