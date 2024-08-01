package com.nurhossain.lifeplusbdtask.api.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nurhossain.lifeplusbdtask.api.models.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    // Dao interface for the database
    abstract fun userDao() : UserDao
    companion object {
        @Volatile
        var INSTANCE : UserDatabase?=null

        // Singleton instance of the database
        fun getDatabaseInstance(context : Context) : UserDatabase {
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            // Synchronized block to make sure that
            // only one instance of the database is created
            synchronized(this){
                val roomDatabaseInstance = Room.databaseBuilder(context, UserDatabase::class.java,"Contacts").allowMainThreadQueries().build()
                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }

    }
}
