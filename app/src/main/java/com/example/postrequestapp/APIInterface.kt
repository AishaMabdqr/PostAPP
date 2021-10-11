package com.example.postrequestapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @POST("/test/")
    fun addItems(@Body ItemsData: AddDetails): Call<AddDetails>

    @GET("/test/")
    fun getItems() : Call<List<Details.Items>>
}