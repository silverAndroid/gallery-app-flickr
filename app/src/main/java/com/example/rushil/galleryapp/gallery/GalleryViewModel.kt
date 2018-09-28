package com.example.rushil.galleryapp.gallery

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.experimental.Job
import javax.inject.Inject

class GalleryViewModel @Inject constructor(private val galleryService: GalleryService) : ViewModel() {
    private val jobs = mutableListOf<Job>()

    suspend fun getImages(): List<Image> {
        return galleryService
            .getImages()
            .apply {
                jobs.add(this)
                this.invokeOnCompletion { jobs.remove(this) }
            }
            .await().photos.photo
    }

    override fun onCleared() {
        jobs.filter { it.isActive }.forEach { it.cancel() }
        super.onCleared()
    }
}