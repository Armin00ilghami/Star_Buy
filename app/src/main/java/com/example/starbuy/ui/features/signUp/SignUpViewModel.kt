package com.example.starbuy.ui.features.signUp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starbuy.model.repository.user.UserRepository
//import com.example.starbuy.model.repository.user.UserRepository
//import com.example.starbuy.util.coroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel( private val repository: UserRepository) : ViewModel() {
    val name = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val confirmPassword = MutableLiveData("")

    fun signUpUser() {

    }

}