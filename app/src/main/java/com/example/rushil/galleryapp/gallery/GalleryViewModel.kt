package com.example.rushil.galleryapp.gallery

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Job
import javax.inject.Inject

class GalleryViewModel @Inject constructor(private val galleryService: GalleryService): ViewModel() {
    private val jobs = mutableListOf<Job>()

    fun getImages(): Deferred<ImageResponse> {
        return galleryService.getImages().apply {
            jobs.add(this)
            this.invokeOnCompletion { jobs.remove(this) }
        }
    }

    override fun onCleared() {
        jobs.filter { it.isActive }.forEach { it.cancel() }
        super.onCleared()
    }
}