package com.example.rushil.galleryapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rushil.galleryapp.di.ViewModelFactory
import com.example.rushil.galleryapp.di.ViewModelKey
import com.example.rushil.galleryapp.gallery.GalleryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GalleryViewModel::class)
    internal abstract fun galleryViewModel(viewModel: GalleryViewModel): ViewModel
}