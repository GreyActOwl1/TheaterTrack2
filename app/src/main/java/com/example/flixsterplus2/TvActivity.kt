package com.example.flixsterplus2

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

private const val TAG = "TvActivity"

class TvActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tv)
//TODO: Refactor Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // TODO: Refactor - setOnNavigationItemSelectedListener is deprecated
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.nav_tv
        bottomNavigationView.setOnNavigationItemSelectedListener{ item ->
            when(item.itemId) {
                R.id.nav_movies -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_tv -> {

                    Toast.makeText(this, "Scroll for more TV Shows", Toast.LENGTH_SHORT).show()

                    // Do Nothing - This is the current activity
                    true
                }
                else -> false
            }
        }

        val client = AsyncHttpClient()
        val params = RequestParams()
        //TODO: Refactor Movie to TV

        val moviesRecyclerView = findViewById<RecyclerView>(R.id.movies_recycler_view)



        client["https://api.themoviedb.org/3/tv/popular?page=1&api_key=${MainActivity.API_KEY}", params, object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                //TODO: Refactor with Retrofit and Gson
                Log.d(TAG, json.jsonObject.toString())
                val resultsJSON: JSONArray = json.jsonObject.get("results") as JSONArray
                val moviesRawJSON: String = resultsJSON.toString()

                val gson = Gson()
                val arrayMovieType = object : TypeToken<List<MediaItem>>() {}.type
                val models: List<MediaItem> = gson.fromJson(moviesRawJSON, arrayMovieType)

                val isPortrait =
                    resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

                moviesRecyclerView.apply {
                    adapter = MediaAdapter(this@TvActivity, isPortrait, models)
                    layoutManager = LinearLayoutManager(this@TvActivity)
                    addItemDecoration(DividerItemDecoration(this@TvActivity, DividerItemDecoration.VERTICAL))
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?

            ) {

                Log.d(TAG, "https://api.themoviedb.org/3/tv/popular?page=1&api_key=${MainActivity.API_KEY}")
                Log.d(TAG, response)
            }
        }]
    }

}
