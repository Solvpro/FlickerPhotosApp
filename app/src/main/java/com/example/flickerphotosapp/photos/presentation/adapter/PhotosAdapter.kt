package com.example.flickerphotosapp.photos.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.flickerphotosapp.databinding.ItemAdBannerBinding
import com.example.flickerphotosapp.databinding.ItemPhotoBinding
import com.example.flickerphotosapp.photos.domain.entity.PhotoEntity
import com.squareup.picasso.Picasso

class PhotosAdapter(private val itemClick: (PhotoEntity) -> Unit) :
    PagingDataAdapter<PhotoEntity, RecyclerView.ViewHolder>(PhotoEntityDiffCallback()) {

    companion object {
        private const val PHOTO_VIEW_TYPE = 1
        private const val AD_BANNER_VIEW_TYPE = 2
        private const val AD_BANNER_INTERVAL = 6
        private const val IMAGE_SIDE_PX = 400
    }

    override fun getItemViewType(position: Int): Int {
        return if ((position + 1) % AD_BANNER_INTERVAL == 0) {
            AD_BANNER_VIEW_TYPE
        } else {
            PHOTO_VIEW_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PHOTO_VIEW_TYPE -> {
                val view =
                    ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                PhotoViewHolder(view)
            }

            AD_BANNER_VIEW_TYPE -> {
                val view =
                    ItemAdBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                AdBannerViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            PHOTO_VIEW_TYPE -> {
                val photoPosition = getPhotoPosition(position)
                val photo = getItem(photoPosition)
                (holder as PhotoViewHolder).bind(photo)
                holder.itemView.setOnClickListener { photo?.let { it1 -> itemClick(it1) } }
            }
            AD_BANNER_VIEW_TYPE -> {
                (holder as AdBannerViewHolder).bind()
            }
        }
    }

    private fun getPhotoPosition(adapterPosition: Int): Int {
        return adapterPosition - (adapterPosition + 1) / AD_BANNER_INTERVAL
    }

    inner class PhotoViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: PhotoEntity?) {
            Picasso.get().load(photo?.imageUrl).resize(IMAGE_SIDE_PX, IMAGE_SIDE_PX)
                .centerCrop().into(binding.ivPhoto)
        }
    }

    inner class AdBannerViewHolder(private val binding: ItemAdBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
        }
    }

    class PhotoEntityDiffCallback : DiffUtil.ItemCallback<PhotoEntity>() {
        override fun areItemsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: PhotoEntity, newItem: PhotoEntity
        ): Boolean {
            return oldItem == newItem
        }
    }
}