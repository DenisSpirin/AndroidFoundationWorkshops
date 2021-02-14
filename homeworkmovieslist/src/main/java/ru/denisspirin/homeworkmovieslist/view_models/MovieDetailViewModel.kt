package ru.denisspirin.homeworkmovieslist.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.denisspirin.homeworkmovieslist.data.MoviesLoader
import ru.denisspirin.homeworkmovieslist.data.models.Movie
import ru.denisspirin.homeworkmovieslist.db.MoviesRepository

class MovieDetailViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private val moviesLoader = MoviesLoader()

    private val _mutableMovie =  MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _mutableMovie

    /*
    private fun saveData(movie: Movie) {
        viewModelScope.launch {
            moviesRepository.addActorsForMovie(movie.id, movie.actors)
        }
    }*/

    fun updateData(movieId: Int) {
        viewModelScope.launch {
            _mutableMovie.value = moviesRepository.getMovie(movieId)

            val loadedMovie = moviesLoader.getMovieDetail(movieId)
            _mutableMovie.value = loadedMovie

            //saveData(loadedMovie)
        }
    }
}