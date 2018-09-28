package com.example.rushil.galleryapp.gallery

import android.app.Application
import com.example.rushil.galleryapp.di.component.DaggerGalleryComponent
import com.example.rushil.galleryapp.di.component.GalleryComponent

class GalleryApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        daggerComponent = DaggerGalleryComponent.create()
    }

    companion object {
        lateinit var daggerComponent: GalleryComponent
    }
}