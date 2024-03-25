package com.example.flixsterplus2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.toAndroidPair
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import androidx.core.util.Pair as UtilPair


private const val TAG = "MediaAdapter"
class MediaAdapter(val context: Context, private val isPortrait: Boolean, private val moviesList: List<MediaItem>) :
    RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val mediaImageView: ImageView = itemView.findViewById(R.id.media_image_view)
        val mediaTitle: TextView = itemView.findViewById(R.id.media_title_text_view)
        val mediaDescription: TextView =
            itemView.findViewById(R.id.media_description_text_view)
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {

            Log.d(TAG, "onClick: clicked on ${mediaTitle.text}")

            val movie = moviesList[adapterPosition]

            val intent = Intent(context, MediaDetailActivity::class.java).apply {
                putExtra("MEDIA_ITEM", movie)
            }

if (isPortrait) {
    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
        context as Activity,
        UtilPair(itemView.findViewById<View>(R.id.media_image_view), mediaImageView.transitionName),
        UtilPair(itemView.findViewById<View>(R.id.media_title_text_view), mediaTitle.transitionName),

        )

    context.startActivity(intent, options.toBundle())
} else {
    context.startActivity(intent)
}

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MediaAdapter.ViewHolder {


        //TODO: Check if the orientation is landscape
        return if (isPortrait) {
            // Inflate default layout (portrait)
            ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.media_item_view, parent, false))

        } else {
            // Inflate landscape layout
            ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.media_item_view_land, parent, false))

        }


    }

    override fun onBindViewHolder(holder: MediaAdapter.ViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.mediaTitle.text = movie.title
//        holder.movieDescription.text = movie.description
        if (isPortrait) {
            Glide.with(holder.itemView)
                .load(movie.posterImageUrl)
                .placeholder(R.drawable.placeholder_300)
                .centerInside()
                .into(holder.mediaImageView)
        } else {
            Glide.with(holder.itemView)
                .load(movie.backdropImageUrl)
                .placeholder(R.drawable.placeholder_300)
                .centerInside()
                .into(holder.mediaImageView)
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }



}