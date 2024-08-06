package com.example.starbuy.model.repository.product

import com.example.starbuy.model.data.Ads
import com.example.starbuy.model.data.Product

interface ProductRepository {

    suspend fun getAllProducts(isInternetConnected :Boolean): List<Product>
    suspend fun getAllAds(isInternetConnected :Boolean): List<Ads>

}