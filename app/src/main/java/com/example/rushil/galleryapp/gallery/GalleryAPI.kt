package com.example.rushil.galleryapp.gallery

import com.example.rushil.galleryapp.BuildConfig
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class GalleryService @Inject constructor(retrofit: Retrofit) {
    private val api = retrofit.create(GalleryAPI::class.java)

    fun getImages(): Deferred<ImageResponse> = api.getImages()

    interface GalleryAPI {
        @GET("/services/rest")
        fun getImages(
            @Query("api_key") apiKey: String = BuildConfig.API_KEY,
            @Query("method") m: String = "flickr.photos.getRecent",
            @Query("format") f: String = "json",
            @Query("nojsoncallback") n: String = "?"
        ): Deferred<ImageResponse>
    }
}