package com.nurhossain.lifeplusbdtask.viemodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.nurhossain.lifeplusbdtask.api.models.User
import com.nurhossain.lifeplusbdtask.api.room.UserDatabase
import com.nurhossain.lifeplusbdtask.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository
    val saveStatus = MutableLiveData<Boolean>()
    init {
        val userDao = UserDatabase.getDatabaseInstance(application).userDao()
        repository = UserRepository(userDao)
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertUser(user)
                saveStatus.postValue(true)
            } catch (e: Exception) {
                saveStatus.postValue(false)
            }
        }
    }
    //fun getUser() : LiveData<List<User>> = repository.getUser()

    fun getUserByUsername(username: String) = liveData(Dispatchers.IO) {
        val user = repository.getUserByUsername(username)
        emit(user)
    }

    /*fun getUser(username: String, password: String) = liveData(Dispatchers.IO) {
        val user = repository.getUser(username, password)
        emit(user)
    }*/

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    fun getValidUser(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = repository.getValidUser(username, password)
            _user.postValue(user)
        }
    }
}

