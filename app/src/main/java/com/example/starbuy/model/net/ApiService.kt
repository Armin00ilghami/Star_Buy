package com.example.starbuy.model.net

import com.example.starbuy.model.data.LoginResponse
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("signUp")
    suspend fun signUp(@Body jsonObject: JsonObject):LoginResponse
    @POST("signIn")
    suspend fun signIn(@Body jsonObject: JsonObject):LoginResponse

}