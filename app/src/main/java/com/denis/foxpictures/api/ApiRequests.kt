package com.denis.foxpictures.api

import retrofit2.http.GET

interface ApiRequests {

    @GET("/floof")
    suspend fun getFoxPicture(): FoxJson
}