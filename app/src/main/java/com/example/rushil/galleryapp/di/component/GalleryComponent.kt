package com.example.rushil.galleryapp.di.component

import com.example.rushil.galleryapp.MainActivity
import com.example.rushil.galleryapp.di.module.GalleryModule
import dagger.Component

@Component(modules = [GalleryModule::class])
interface GalleryComponent {
    fun inject(activity: MainActivity)
}