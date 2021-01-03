package ru.denisspirin.homeworkmovieslist.data

import android.content.Context
import kotlinx.coroutines.runBlocking
import ru.denisspirin.homeworkmovieslist.data.loadMovies
import ru.denisspirin.homeworkmovieslist.data.models.Movie

class MoviesLoaderMock(
    private val context: Context
        ) {
    private var movies: List<Movie>? = null
    private var movie: Movie? = null

    fun getMovies(): List<Movie>? {
        runBlocking {
            movies = loadMovies(context)
        }
        return movies
    }

    fun getMovie(movieId: Int): Movie? {
        runBlocking {
            movie = loadMovie(context, movieId)
        }
        return movie
    }
}