package com.example.flixsterplus2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "MediaDetailActivity"

class MediaDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_detail)

       val mediaImageView = findViewById<ImageView>(R.id.mediaImageView)
        val titleTextView = findViewById<TextView>(R.id.mediaTitleView)
//       val taglineTextView = findViewById<TextView>(R.id.taglineTextView)
       val lastAirDateTextView = findViewById<TextView>(R.id.lastAirDateTextView)
        val descriptionTextView = findViewById<TextView>(R.id.media_description_text_view)
        // Included for future use
//       val nextAirDateTextView = findViewById<TextView>(R.id.nextAirDateTextView)

        val media = intent.getSerializableExtra("MEDIA_ITEM") as MediaItem

        //TODO:Add API call for more details
        //TODO:Edit layout to show more details


        titleTextView.text = media.title
        lastAirDateTextView.text = media.displayDate
        descriptionTextView.text = media.description
        // Included for future use
//        nextAirDateTextView.text = media.nextAirDate

        Glide.with(this)
            .load(media.backdropImageUrl)
            .placeholder(R.drawable.placeholder_300)
            .into(mediaImageView)



    }
}