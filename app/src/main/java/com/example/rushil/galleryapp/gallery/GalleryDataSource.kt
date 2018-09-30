package com.example.rushil.galleryapp.gallery

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.experimental.launch

class GalleryDataSource(private val service: GalleryViewModel) : PageKeyedDataSource<Int, Image>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Image>) {
        launch {
            val imageResponse = service.getImages(perPage = params.requestedLoadSize)
            callback.onResult(imageResponse.photo, 0, imageResponse.total, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {
        launch {
            val page = params.key
            val imageResponse = service.getImages(page, params.requestedLoadSize)
            callback.onResult(imageResponse.photo, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {}
}