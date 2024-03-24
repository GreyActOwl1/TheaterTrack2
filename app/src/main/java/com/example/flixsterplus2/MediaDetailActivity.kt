package com.example.flixsterplus2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

private const val TAG = "MediaDetailActivity"

class MediaDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_detail)

        val mediaImageView = findViewById<ImageView>(R.id.mediaBackdropImageView)
        val titleTextView = findViewById<TextView>(R.id.media_title_text_view)
//       val taglineTextView = findViewById<TextView>(R.id.taglineTextView)
        val lastAirDateTextView = findViewById<TextView>(R.id.lastAirDateTextView)
        val descriptionTextView = findViewById<TextView>(R.id.media_description_text_view)
        val mediaTempImageView = findViewById<ImageView>(R.id.mediaPosterImageView)
        // Included for future use
//       val nextAirDateTextView = findViewById<TextView>(R.id.nextAirDateTextView)
//TODO: Refactor this: getSerializableExtra is deprecated
        val media = intent.getSerializableExtra("MEDIA_ITEM") as MediaItem

        //TODO:Add API call for more details
        //TODO:Edit layout to show more details


        titleTextView.text = media.title
        lastAirDateTextView.text = media.displayDate
        descriptionTextView.text = media.description
        // Included for future use
//        nextAirDateTextView.text = media.nextAirDate

        val radius = 50 // corner radius, higher value = more rounded
        Glide.with(this)
            .load(media.backdropImageUrl)
            .placeholder(R.drawable.placeholder_300)
            .into(mediaImageView)

        Glide.with(this)
            .load(media.posterImageUrl)
            .centerCrop()
            .transform(RoundedCorners(radius))
            .into(mediaTempImageView)


    }
}