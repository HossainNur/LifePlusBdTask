package com.nurhossain.lifeplusbdtask.repository

import androidx.lifecycle.LiveData
import com.nurhossain.lifeplusbdtask.api.models.User
import com.nurhossain.lifeplusbdtask.api.room.UserDao

class UserRepository(val dao : UserDao) {
    // function to get all contacts from the database
    fun getUser() : LiveData<List<User>> {
        return dao.getUser()
    }

    // function to insert a contact in the database
    fun insertUser(user: User) {dao.insertUser(user)}

   /* fun getUser(username: String, password: String): User? {
        return dao.getUser(username, password)
    }*/
     fun getValidUser(username: String, password: String): User? {
        return dao.getValidUser(username, password)
    }

    fun getUserByUsername(username: String): User? {
        return dao.getUserByUsername(username)
    }

}
