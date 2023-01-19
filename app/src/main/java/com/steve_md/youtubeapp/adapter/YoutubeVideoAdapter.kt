package com.steve_md.youtubeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.steve_md.youtubeapp.data.dto.Item
import com.steve_md.youtubeapp.databinding.VideoRowBinding

class YoutubeVideoAdapter : ListAdapter<Item, YoutubeVideoAdapter.YoutubeVideoViewHolder>(YoutubeVideoDiffUtil) {
    object YoutubeVideoDiffUtil : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }

    inner class YoutubeVideoViewHolder(private val binding: VideoRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(youtubeVideo: Item?) {
            binding.textViewVideoTitle.text = youtubeVideo?.snippet?.title
            binding.videoImageView.load(youtubeVideo?.snippet?.thumbnails?.medium?.url)
            binding.imageViewYoutubeChannel.load(youtubeVideo?.snippet?.thumbnails?.medium?.url)
            binding.textViewYoutubeChannelName.text = youtubeVideo?.snippet?.channelTitle
            binding.textViewVideoViews.text = viewCount(youtubeVideo?.statistics?.viewCount?.toInt())

           /* binding.textViewVideoUploadTime.text = youtubeVideo?.snippet?.publishedAt?.let {
                upLoadedAt(
                    it,
                )
            }
            */
        }
    }

       /*private fun upLoadedAt(publishedAt: String): String {

        }*/

    private fun viewCount(countViews: Int?): String {
        return when {
            countViews!! >= 1000000000 -> {
                val countViewsFormat = countViews / 1000000000
                "${countViewsFormat}B views"
            }
            countViews >= 1000000 -> {
                val countViewsFormat = countViews / 1000000
                "${countViewsFormat}M views"
            }
            countViews >= 1000 -> {
                val countViewsFormat = countViews / 1000
                "${countViewsFormat}K views"
            }
            else -> {
                "$countViews views"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeVideoViewHolder {
        return YoutubeVideoViewHolder(
            VideoRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: YoutubeVideoViewHolder, position: Int) {
        val youtubeVideo = getItem(position)
        holder.bind(youtubeVideo)
    }

        /*
            override fun getItemCount(): Int {
                return itemCount
            }
         */

}