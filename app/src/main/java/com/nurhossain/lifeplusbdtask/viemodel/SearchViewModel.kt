package com.nurhossain.lifeplusbdtask.viemodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nurhossain.lifeplusbdtask.api.models.SearchResponse
import com.nurhossain.lifeplusbdtask.repository.SearchRepository

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: SearchRepository = SearchRepository(application)
    private val _searchDetails = MutableLiveData<SearchResponse?>()
    val searchContent: LiveData<SearchResponse?> = _searchDetails

    fun getSearchContents(query: String) {
        val data = repository.getSearchContents(query)
        _searchDetails.postValue(data.value)
    }
}