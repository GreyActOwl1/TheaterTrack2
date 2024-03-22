package com.example.flixsterplus2

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import okhttp3.Headers
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    //TODO: Add debug tags
    // TODO: Update Readme.md
    // TODO: Add NavBar
    companion object {
        private const val API_KEY = BuildConfig.MOVIES_DB_API_KEY
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val client = AsyncHttpClient()
        val params = RequestParams()
        val moviesRecyclerView = findViewById<RecyclerView>(R.id.movies_recycler_view)



            client["https://api.themoviedb.org/3/movie/now_playing?&api_key=${API_KEY}", params, object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                //TODO: Refactor with Retrofit and Gson
                Log.d("DEBUG OBJECT", json.jsonObject.toString())
                val resultsJSON: JSONArray = json.jsonObject.get("results") as JSONArray
                val moviesRawJSON: String = resultsJSON.toString()

                val gson = Gson()
                val arrayMovieType = object : TypeToken<List<MovieItem>>() {}.type
                val models: List<MovieItem> = gson.fromJson(moviesRawJSON, arrayMovieType)

                val isPortrait =
                    resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

                moviesRecyclerView.apply {
                    adapter = MoviesAdapter(isPortrait, models)
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

                Log.d("DEBUG", response)
            }
        }]
        //TODO: Add infinite scroll
        //TODO: (Optional) If time allows migrate secrets
    }


}
