package com.example.flixsterplus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesAdapter(private val moviesList: List<MovieItem>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImageView: ImageView = itemView.findViewById<ImageView>(R.id.movie_image_view)
        val movieTitle: TextView = itemView.findViewById<TextView>(R.id.movie_title_text_view)
        val movieDescription: TextView = itemView.findViewById<TextView>(R.id.movie_description_text_view)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_view, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {
        val movie = moviesList[position]
         holder.movieTitle.text = movie.title
        holder.movieDescription.text = movie.description
        Glide.with(holder.itemView)
             .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .placeholder(R.drawable.placeholder_300)
             .centerInside()
             .into(holder.movieImageView)
    }

    override fun getItemCount(): Int {
       return moviesList.size
    }

}