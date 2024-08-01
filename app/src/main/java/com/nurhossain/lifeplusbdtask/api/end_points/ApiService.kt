package com.nurhossain.lifeplusbdtask.api.end_points


import com.nurhossain.lifeplusbdtask.api.models.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("ott-content/search")
    fun getSearch(@Query("q") query: String): Call<SearchResponse?>?
}