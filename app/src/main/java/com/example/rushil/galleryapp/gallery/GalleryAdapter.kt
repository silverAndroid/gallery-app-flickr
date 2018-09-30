package com.example.rushil.galleryapp.gallery

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.example.rushil.galleryapp.R

class GalleryAdapter(images: PagedList<Image>, private val glide: RequestManager) : PagedListAdapter<Image, GalleryViewHolder>(diffCallback) {
    init {
        submitList(images)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_image_card, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val image = getItem(position)
        if (image != null) {
            val title = image.title.trim()

            holder.title.text = title
            if (title.isBlank()) holder.title.visibility = View.GONE
            else holder.title.visibility = View.VISIBLE

            val loadingOptions = RequestOptions().override(300)
            glide
                .asBitmap()
                .load("https://farm${image.farm}.staticflickr.com/${image.server}/${image.id}_${image.secret}.jpg")
                .apply(loadingOptions)
                .into(object : CustomViewTarget<ImageView, Bitmap>(holder.image) {
                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        holder.image.setImageDrawable(errorDrawable)
                    }

                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        val dimensions = "${resource.width}x${resource.height}"
                        holder.dimensions.text = dimensions

                        val fileSize = resource.byteCount / 1024f
                        if (fileSize < 1024) holder.imageSize.text = holder.image.context.getString(R.string.file_size_kb_template, Math.round(fileSize))
                        else holder.imageSize.text = holder.image.context.getString(R.string.file_size_mb_template, fileSize / 1024f)

                        holder.image.setImageBitmap(resource)
                    }

                    override fun onResourceCleared(placeholder: Drawable?) {
                        holder.image.setImageDrawable(placeholder)
                    }
                })
        }
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
