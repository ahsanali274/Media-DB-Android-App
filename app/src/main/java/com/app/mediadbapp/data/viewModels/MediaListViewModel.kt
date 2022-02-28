package com.app.mediadbapp.data.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.app.mediadbapp.R
import com.app.mediadbapp.data.models.Movie
import com.google.gson.Gson

class MediaListViewModel : ViewModel() {

    val moviesList: List<Movie> get() = listOf()
    fun getList(context: Context): List<Pair<String, List<Movie>>> {
        val data: String = context.resources.openRawResource(R.raw.movie_list)
            .bufferedReader()
            .use { it.readText() }

        return listOf(
            Pair("Trending", Gson().fromJson(data, Array<Movie>::class.java).asList()),
            Pair("Popular", Gson().fromJson(data, Array<Movie>::class.java).asList())
        )
    }
}