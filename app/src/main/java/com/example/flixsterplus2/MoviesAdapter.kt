package com.example.flixsterplus2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

private const val TAG = "MoviesAdapter"
class MoviesAdapter(private val isPortrait: Boolean, private val moviesList: List<MovieItem>) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImageView: ImageView = itemView.findViewById(R.id.movie_image_view)
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title_text_view)
        val movieDescription: TextView =
            itemView.findViewById(R.id.movie_description_text_view)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.ViewHolder {

        //TODO: Add onClickListener to the ViewHolder
        // Check if the orientation is landscape
        return if (isPortrait) {
            // Inflate default layout (portrait)
            ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.movie_item_view, parent, false))

        } else {
            // Inflate landscape layout
            ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_item_view_land, parent, false))

        }


    }

    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.movieTitle.text = movie.title
        holder.movieDescription.text = movie.description
        if (isPortrait) {
            Glide.with(holder.itemView)
                .load(movie.posterImageUrl)
                .placeholder(R.drawable.placeholder_300)
                .centerInside()
                .into(holder.movieImageView)
        } else {
            Glide.with(holder.itemView)
                .load(movie.backdropImageUrl)
                .placeholder(R.drawable.placeholder_300)
                .centerInside()
                .into(holder.movieImageView)
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }



}