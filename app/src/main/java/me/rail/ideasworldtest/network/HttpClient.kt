package me.rail.ideasworldtest.network

import me.rail.ideasworldtest.models.item.Item
import me.rail.ideasworldtest.models.list.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HttpClient {
    @GET("/photos")
    suspend fun getPhotos(
        @Query("username") username: String = "Rail Meshcherov",
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 30,
    ): Response<MutableList<Photo>>

    @GET("/photos/{id}")
    suspend fun getItem(
        @Path("id") id: String,
        @Query("username") username: String = "Rail Meshcherov"
    ): Response<Item>
}