package com.nurhossain.lifeplusbdtask.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.nurhossain.lifeplusbdtask.api.end_points.ApiService
import com.nurhossain.lifeplusbdtask.api.end_points.RetrofitClient
import com.nurhossain.lifeplusbdtask.api.models.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepository(private val application: Application) {
    private val apiService: ApiService
    private val searchData: MutableLiveData<SearchResponse?>

    init {
        apiService = RetrofitClient.apiService
        searchData = MutableLiveData()
    }

    fun getSearchContents(keyword: String): MutableLiveData<SearchResponse?> {
        val call = apiService.getSearch(keyword)
        call?.enqueue(object : Callback<SearchResponse?> {
            override fun onResponse(
                call: Call<SearchResponse?>,
                response: Response<SearchResponse?>
            ) {
                if (response.isSuccessful && response.body() != null && response.body()!!.data != null) {
                    searchData.setValue(response.body())
                } else {
                    Toast.makeText(application, response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {
                Toast.makeText(application, "Failed to connect", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
        return searchData
    }
}
