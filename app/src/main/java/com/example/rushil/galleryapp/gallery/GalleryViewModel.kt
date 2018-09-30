package com.example.rushil.galleryapp.gallery

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.experimental.Job
import javax.inject.Inject

class GalleryViewModel @Inject constructor(private val galleryService: GalleryService) : ViewModel() {
    private val jobs = mutableListOf<Job>()

    suspend fun getImages(page: Int = 1, perPage: Int = 100): PhotosResponse {
        return galleryService
            .getImages(page, perPage)
            .apply {
                jobs.add(this)
                this.invokeOnCompletion { jobs.remove(this) }
            }
            .await().photos
    }

    override fun onCleared() {
        jobs.filter { it.isActive }.forEach { it.cancel() }
        super.onCleared()
    }
}