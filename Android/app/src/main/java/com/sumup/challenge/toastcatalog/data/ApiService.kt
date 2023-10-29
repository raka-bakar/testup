package com.sumup.challenge.toastcatalog.data

import com.sumup.challenge.toastcatalog.model.ResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("items/")
    suspend fun getToastList(): Response<List<ResponseItem>>
}