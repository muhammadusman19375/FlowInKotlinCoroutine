package com.example.flowincoroutinekotlin.data

import retrofit2.http.GET

interface Api {

    @GET("/posts")
    suspend fun getData(): List<Post>

}