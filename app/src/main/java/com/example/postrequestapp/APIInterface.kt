package com.example.postrequestapp

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    @POST("/test/")
    fun addItems(@Body ItemsData: AddDetails): Call<AddDetails>

    @GET("/test/")
    fun getItems() : Call<List<Details.Items>>

    @PUT ("/test/{id}")
    fun updateUser(@Path("id") id: Int, @Body userData:Details.Items) : Call<Details.Items>

    @DELETE ("/test/{id}")
    fun deleteUser(@Path("id") id: Int): Call<Void>
}