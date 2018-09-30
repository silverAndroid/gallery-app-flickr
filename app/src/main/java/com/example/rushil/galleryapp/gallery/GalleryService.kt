package com.example.rushil.galleryapp.gallery

import com.example.rushil.galleryapp.BuildConfig
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class GalleryService @Inject constructor(retrofit: Retrofit) {
    private val api = retrofit.create(GalleryAPI::class.java)

    fun getImages(page: Int = 1, perPage: Int = 100): Deferred<ImageResponse> = api.getImages(page, perPage)

    interface GalleryAPI {
        @GET("/services/rest")
        fun getImages(
            @Query("page") page: Int = 1,
            @Query("per_page") perPage: Int = 100,
            @Query("api_key") apiKey: String = BuildConfig.API_KEY,
            @Query("method") m: String = "flickr.photos.getRecent",
            @Query("format") f: String = "json",
            @Query("nojsoncallback") n: String = "?"
        ): Deferred<ImageResponse>
    }
}