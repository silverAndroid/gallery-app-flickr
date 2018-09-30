package com.example.rushil.galleryapp.gallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.rushil.galleryapp.MainThreadExecutor
import com.example.rushil.galleryapp.R
import com.example.rushil.galleryapp.di.withViewModel
import kotlinx.android.synthetic.main.activity_gallery.*
import javax.inject.Inject

class GalleryActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        GalleryApplication.daggerComponent.inject(this)

        withViewModel<GalleryViewModel>(viewModelFactory) {
            val uiExecutor = MainThreadExecutor()
            val dataSource = GalleryDataSource(this)
            val config = PagedList.Config.Builder()
                .setPageSize(50)
                .setInitialLoadSizeHint(100)
                .setEnablePlaceholders(true)
                .build()
            val images = PagedList.Builder(dataSource, config)
                // since all the network requests are already going on a separate thread,
                // it should be fine to use the main thread executor here
                .setFetchExecutor(uiExecutor)
                .setNotifyExecutor(uiExecutor)
                .build()

            imageGrid.adapter = GalleryAdapter(images, Glide.with(this@GalleryActivity))
            imageGrid.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }
}
