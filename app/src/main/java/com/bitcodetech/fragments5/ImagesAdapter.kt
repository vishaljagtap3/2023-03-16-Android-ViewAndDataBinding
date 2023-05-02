package com.bitcodetech.fragments5

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ImagesAdapter(
    val imageIds : ArrayList<Int>
) : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

    interface OnImageClickListener {
        fun onImageClick(imagesAdapter: ImagesAdapter, imageId : Int, position : Int)
    }

    var onImageClickListener : OnImageClickListener? = null

    inner class ImageViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView) {
        init {
            imageView.setOnClickListener {
                onImageClickListener?.onImageClick(
                    this@ImagesAdapter,
                    imageIds[adapterPosition],
                    adapterPosition
                )
            }
        }
    }

    override fun getItemCount() = imageIds.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val img = LayoutInflater.from(parent.context).inflate(R.layout.image_view, null) as ImageView
        return ImageViewHolder(img)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.imageView.setImageResource(
            imageIds[position]
        )
    }
}