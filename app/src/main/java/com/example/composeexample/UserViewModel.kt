package com.example.composeexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeexample.model.User
import com.example.composeexample.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val users: LiveData<List<User>> by lazy {
        userRepository.getAllUser()
    }

    val isLoading: LiveData<Boolean> get() = _isLoading

    fun addUser() {
        if (_isLoading.value == false) {
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.postValue(true)
                userRepository.getNewUser()
                _isLoading.postValue(false)
            }
        }
    }

    fun deleteUser(toDeleteUser: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteUser(toDeleteUser)

        }
    }
}