package com.example.rushil.galleryapp.gallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.rushil.galleryapp.R
import com.example.rushil.galleryapp.di.withViewModel
import kotlinx.android.synthetic.main.activity_gallery.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import javax.inject.Inject

class GalleryActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        GalleryApplication.daggerComponent.inject(this)

        withViewModel<GalleryViewModel>(viewModelFactory) {
            launch {
                val images = getImages()
                withContext(UI) {
                    imageGrid.adapter = GalleryAdapter(images, Glide.with(this@GalleryActivity))
                    imageGrid.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                }
            }
        }
    }
}
