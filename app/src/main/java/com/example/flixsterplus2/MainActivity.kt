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

private const val TAG = "MainActivity/ "

class MainActivity : AppCompatActivity() {
    // TODO: Update Readme.md
    // TODO: Add NavBar
    companion object {
        const val API_KEY = BuildConfig.MOVIES_DB_API_KEY
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // TODO: Refactor - setOnNavigationItemSelectedListener is deprecated

         val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.nav_movies
        bottomNavigationView.setOnNavigationItemSelectedListener{ item ->
            when(item.itemId) {
                R.id.nav_movies -> {
                    Toast.makeText(this, "Scroll for more Movies", Toast.LENGTH_SHORT).show()
                    // Do Nothing - This is the current activity
                    true
                }
                R.id.nav_tv -> {
                    val TvIntent = Intent(this, TvActivity::class.java)
                    startActivity(TvIntent)
                    true
                }
                else -> false
            }
        }


        val client = AsyncHttpClient()
        val params = RequestParams()
        val moviesRecyclerView = findViewById<RecyclerView>(R.id.movies_recycler_view)



            client["https://api.themoviedb.org/3/movie/now_playing?&api_key=$API_KEY", params, object :
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
                    adapter = MediaAdapter(this@MainActivity,isPortrait, models)
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?

            ) {

                Log.d(TAG, "https://api.themoviedb.org/3/movie/now_playing&api_key=$API_KEY")
                Log.d(TAG, response)
            }
        }]
        //TODO: Add infinite scroll
        //TODO: (Optional) If time allows migrate secrets
    }


}
