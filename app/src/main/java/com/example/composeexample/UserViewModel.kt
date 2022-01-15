package com.example.composeexample

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeexample.repository.UserRepository
import com.example.composeexample.repository.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepositoryImpl: UserRepository
) : ViewModel() {

    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userRepositoryImpl.getNewUser()
            Log.d("josue", user.toString() )
        }
    }
}