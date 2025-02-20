package com.baeolian.idealisto.data.service

import retrofit2.http.GET
import com.baeolian.idealisto.data.model.PropertyDetailsEntity
import com.baeolian.idealisto.data.model.PropertyEntity

interface ApiService {

    @GET("list.json")
    suspend fun getPropertyList(): List<PropertyEntity>

    @GET("detail.json")
    suspend fun getPropertyDetails(): PropertyDetailsEntity
}
