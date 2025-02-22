package com.example.starbuy.model.repository.user

import android.content.SharedPreferences
import com.example.starbuy.model.net.ApiService
import com.example.starbuy.model.repository.TokenInMemory
import com.example.starbuy.util.VALUE_SUCCESS
import com.google.gson.JsonObject

class UserRepositoryImpl(
    private val apiService: ApiService,
    private val sharedPref: SharedPreferences
) : UserRepository {
    //online
    override suspend fun signUp(name: String, username: String, password: String):String {

        val jsonObject = JsonObject().apply {
            addProperty("name", name)
            addProperty("email", username)
            addProperty("password", password)
        }

        val result = apiService.signUp(jsonObject)
        if (result.success) {

            TokenInMemory.refreshToken(username, result.token)
            saveToken(result.token)
            saveUserName(username)

            return VALUE_SUCCESS
        } else {
            return result.message
        }

    }

    override suspend fun signIn(username: String, password: String):String {

        val jsonObject = JsonObject().apply {
            addProperty("email", username)
            addProperty("password", password)
        }

        val result = apiService.signIn(jsonObject)
        if (result.success) {

            TokenInMemory.refreshToken(username, result.token)
            saveToken(result.token)
            saveUserName(username)

            return VALUE_SUCCESS
        } else {
            return result.message
        }

    }

    //offline
    override fun signOut() {
        TokenInMemory.refreshToken(null, null)
        sharedPref.edit().clear().apply()
    }

    //load token in sharePref into cache
    override fun loadToken() {
        TokenInMemory.refreshToken(getUserName(), getToken())
    }

    override fun saveToken(newToken: String) {
        sharedPref.edit().putString("token", newToken).apply()
    }

    override fun getToken(): String {
        return sharedPref.getString("token", null)!!
    }

    override fun saveUserName(username: String) {
        sharedPref.edit().putString("username", username).apply()
    }

    override fun getUserName(): String {
        return sharedPref.getString("username", null)!!
    }

}