package com.example.diplomtest.Network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiForFirebase {
    @GET("get")
    fun getUser(@Query("documentId") documentId: String?): Call<CRUDtimer?>?

    @POST("create")
    fun createUser(@Body user: CRUDtimer?): Call<CRUDtimer?>?

}