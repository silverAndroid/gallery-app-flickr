package com.example.rushil.galleryapp.di.component

import com.example.rushil.galleryapp.gallery.GalleryActivity
import com.example.rushil.galleryapp.di.module.GalleryModule
import com.example.rushil.galleryapp.di.module.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [GalleryModule::class, ViewModelModule::class])
@Singleton
interface GalleryComponent {
    fun inject(activity: GalleryActivity)
}