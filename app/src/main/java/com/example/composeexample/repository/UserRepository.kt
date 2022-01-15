package com.example.composeexample.repository

import androidx.lifecycle.LiveData
import com.example.composeexample.datasource.RestDataSource
import com.example.composeexample.model.User
import com.example.composeexample.model.UserDao
import javax.inject.Inject

interface UserRepository {
    suspend fun getNewUser(): User
    suspend fun deleteUser(toDeleteUser: User)
    suspend fun getAllUser(): LiveData<List<User>>
}

class UserRepositoryImpl @Inject constructor(
    private val dataSource: RestDataSource,
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getNewUser(): User {
        val name = dataSource.getUserName().results[0].name
        val location = dataSource.getUserLocation().results[0].location
        val picture = dataSource.getUserPicture().results[0].picture
        val user = User(name!!.first, name.last, location!!.city!!, picture!!.thumbnail!!)
        userDao.insert(user)
        return user
    }

    override suspend fun deleteUser(toDeleteUser: User) = userDao.delete(toDeleteUser)

    override suspend fun getAllUser(): LiveData<List<User>> = userDao.getAll()

}