package com.example.starbuy.ui.features.signIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starbuy.model.repository.user.UserRepository

class SignInViewModel(private val repository: UserRepository) : ViewModel() {

    val email = MutableLiveData("")
    val password = MutableLiveData("")

    fun signInUser() {

    }

}