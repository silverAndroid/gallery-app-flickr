package com.example.rushil.galleryapp.gallery

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.example.rushil.galleryapp.R
import kotlin.math.roundToInt

class GalleryAdapter(images: List<Image>, private val glide: RequestManager) : ListAdapter<Image, GalleryViewHolder>(diffCallback) {
    init {
        submitList(images)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_image_card, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val image = getItem(position)
        val title = image.title.trim()
        var shouldLoad = true

        holder.title.text = title
        if (title.isBlank()) holder.title.visibility = View.GONE
        else holder.title.visibility = View.VISIBLE
        Log.d("GalleryAdapter", title)

        val loadingOptions = RequestOptions()
            .override(300, 300)
        glide
            .asBitmap()
            .load("https://farm${image.farm}.staticflickr.com/${image.server}/${image.id}_${image.secret}.jpg")
            .apply(loadingOptions)
            .into(object : CustomViewTarget<ImageView, Bitmap>(holder.image) {
                override fun onLoadFailed(errorDrawable: Drawable?) {
                    holder.image.setImageDrawable(errorDrawable)
                }

                override fun onResourceCleared(placeholder: Drawable?) {
                    shouldLoad = false
                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    if (shouldLoad) {
                        val dimensions = "${resource.width}x${resource.height}"
                        holder.dimensions.text = dimensions
                    }

                    if (shouldLoad) {
                        val fileSize = resource.byteCount / 1024f
                        holder.imageSize.text = if (fileSize < 1024) {
                            holder.image.context.getString(R.string.file_size_kb_template, fileSize.roundToInt())
                        } else {
                            holder.image.context.getString(R.string.file_size_mb_template, fileSize / 1024f)
                        }
                    }

                    if (shouldLoad) holder.image.setImageBitmap(resource)
                }
            })
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem == newItem
            }
        }
    }
}