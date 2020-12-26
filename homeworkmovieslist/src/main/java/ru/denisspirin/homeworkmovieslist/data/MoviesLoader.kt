package ru.denisspirin.homeworkmovieslist.data

import android.content.Context
import kotlinx.coroutines.runBlocking
import ru.denisspirin.homeworkmovieslist.data.loadMovies
import ru.denisspirin.homeworkmovieslist.data.models.Movie

class MoviesLoader(
    private val context: Context
    ) {
    private var movies: List<Movie>? = null

    fun getMovies(): List<Movie> {
        runBlocking {
            movies = loadMovies(context)
        }
        return movies!!
    }
}