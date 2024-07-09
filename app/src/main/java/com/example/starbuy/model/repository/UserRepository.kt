package com.example.starbuy.model.repository

interface UserRepository {

    //online
    suspend fun signUp(name: String, username: String, password: String)
    suspend fun signIn(username: String , password: String)

    //offline
    fun signOut()
    fun loadToken()

    fun saveToken(newToken: String)
    fun getToken():String

    fun saveUserName(username: String)
    fun getUserName(): String

}