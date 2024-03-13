package com.example.flixsterplus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

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
         holder.movieTitle.text = MovieItem.title
        holder.movieDescription.text = MovieItem.description
        Glide.with(holder.itemView)
             .load(MovieItem.poster_url)
            .placeholder(R.drawable.placeholder_300)
             .centerInside()
             .into(holder.movieImageView)
    }

    override fun getItemCount(): Int {
       return 5
    }

}