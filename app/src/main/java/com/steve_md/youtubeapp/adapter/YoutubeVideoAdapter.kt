package com.steve_md.youtubeapp.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.steve_md.youtubeapp.data.dto.Item
import com.steve_md.youtubeapp.databinding.VideoRowBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

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

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(youtubeVideo: Item?) {
            binding.textViewVideoTitle.text = youtubeVideo?.snippet?.title
            binding.videoImageView.load(youtubeVideo?.snippet?.thumbnails?.medium?.url)
            binding.imageViewYoutubeChannel.load(youtubeVideo?.snippet?.thumbnails?.medium?.url)
            binding.textViewYoutubeChannelName.text = youtubeVideo?.snippet?.channelTitle
            binding.textViewVideoViews.text = viewCount(youtubeVideo?.statistics?.viewCount?.toInt())
            binding.textViewVideoUploadTime.text = formatTime(youtubeVideo?.snippet?.publishedAt)


           /* binding.textViewVideoUploadTime.text = youtubeVideo?.snippet?.publishedAt?.let {
                upLoadedAt(
                    it,
                )
            }
            */
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatTime(publishedAt: String?): String {
        val uploadDateAndTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val uploadedAt = LocalDateTime.parse(publishedAt, uploadDateAndTimeFormat)
        val recentDates = LocalDateTime.now().withNano(0)
        val differenceInSeconds = ChronoUnit.SECONDS.between(uploadedAt, recentDates)
        val differenceInDays = ChronoUnit.DAYS.between(uploadedAt, recentDates)
        val differenceInMonths = ChronoUnit.MONTHS.between(uploadedAt, recentDates)
        return findDifference(differenceInSeconds, differenceInDays, differenceInMonths)
    }

    private fun findDifference(
        differenceInSeconds: Long,
        differenceInDays: Long,
        differenceInMonths: Long
    ): String {
        val hours = differenceInSeconds/3600
        when (differenceInDays) {
            in 25..31 ->{
                return "4 weeks"
            }
            in 21..24 -> {
                return "3 weeks"
            }
            in 14..20 -> {
                return "2 weeks"
            }
            in 8..13 -> {
                return "a week ago"
            }
            in 2..7 -> {
                return "$differenceInDays days ago"
            }
            in 0..1 -> {
                return "$hours hours ago"
            }
        }

        return if (differenceInMonths in 0..1) {
            "$differenceInMonths month ago"
        } else {
            "$differenceInMonths months ago"
        }

    }



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

    @RequiresApi(Build.VERSION_CODES.O)
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