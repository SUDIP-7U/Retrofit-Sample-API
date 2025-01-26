package com.example.retrofitapi

import retrofit2.Response
import retrofit2.http.GET

interface AlbumService {
    @GET("albums")
    suspend fun getalbum() : Response<Album>

}