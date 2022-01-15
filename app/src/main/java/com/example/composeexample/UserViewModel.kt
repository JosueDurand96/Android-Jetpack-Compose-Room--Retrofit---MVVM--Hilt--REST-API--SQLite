package com.example.composeexample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeexample.model.User
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

    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val users:LiveData<List<User>> by lazy {
        userRepositoryImpl.getAllUser()
    }

    val isLoading: LiveData<Boolean> get() = _isLoading

    fun addItem(){
        if (_isLoading.value == false){
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.postValue(true)
                userRepositoryImpl.getNewUser()
                _isLoading.postValue(false)
            }
        }
    }

    fun deleteItem(toDeleteUser: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepositoryImpl.deleteUser(toDeleteUser)

        }
    }
}