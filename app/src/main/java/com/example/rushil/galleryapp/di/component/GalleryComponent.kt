package com.example.rushil.galleryapp.di.component

import com.example.rushil.galleryapp.gallery.GalleryActivity
import com.example.rushil.galleryapp.di.module.GalleryModule
import dagger.Component

@Component(modules = [GalleryModule::class])
interface GalleryComponent {
    fun inject(activity: GalleryActivity)
}