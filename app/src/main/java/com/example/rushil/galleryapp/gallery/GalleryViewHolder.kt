package com.example.rushil.galleryapp.gallery

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rushil.galleryapp.R

class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image: ImageView = itemView.findViewById(R.id.image)
    val title: TextView = itemView.findViewById(R.id.title)
    val dimensions: TextView = itemView.findViewById(R.id.dimensions)
    val imageSize: TextView = itemView.findViewById(R.id.size)
}