package com.example.starbuy.model.net

import com.example.starbuy.model.data.AdsResponse
import com.example.starbuy.model.data.LoginResponse
import com.example.starbuy.model.data.ProductResponse
import com.example.starbuy.model.repository.TokenInMemory
import com.example.starbuy.util.BASE_URL
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("signUp")
    suspend fun signUp(@Body jsonObject: JsonObject):LoginResponse
    @POST("signIn")
    suspend fun signIn(@Body jsonObject: JsonObject):LoginResponse

    @GET("refreshToken")
    fun refreshToken():Call<LoginResponse>

    @GET("getProducts")
    suspend fun getAllProducts() :ProductResponse

    @GET("getSliderPics")
    suspend fun getAllAds() :AdsResponse

}

fun createApiService():ApiService{

    val okhttpClient = OkHttpClient.Builder()
        .addInterceptor{

            val oldRequest = it.request()

            val newRequest = oldRequest.newBuilder()
            if (TokenInMemory.token != null)
                newRequest.addHeader("Authorization",TokenInMemory.token!!)

            newRequest.addHeader("Accept","application/json")
            newRequest.method(oldRequest.method , oldRequest.body)

            return@addInterceptor it.proceed(newRequest.build())
        }.build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttpClient)
        .build()

    return retrofit.create(ApiService::class.java)

}