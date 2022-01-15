package com.example.composeexample.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composeexample.model.User
import com.example.composeexample.model.UserDao

@Database(entities = [User::class], version = 1)
abstract class DbDataSource: RoomDatabase() {

    abstract fun userDao(): UserDao

}