package com.example.flixsterplus

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val moviesRecyclerView = findViewById<RecyclerView>(R.id.movies_recycler_view)

        moviesRecyclerView.adapter = MoviesAdapter()

        moviesRecyclerView.layoutManager = LinearLayoutManager(this)

    }
}
