package com.example.rushil.galleryapp.gallery

class ImageResponse(val photos: PhotosResponse)

class PhotosResponse(val photo: List<Image>, val total: Int)