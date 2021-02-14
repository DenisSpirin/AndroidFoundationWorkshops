package ru.denisspirin.homeworkmovieslist.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.denisspirin.homeworkmovieslist.data.MoviesLoader
import ru.denisspirin.homeworkmovieslist.data.models.Movie
import ru.denisspirin.homeworkmovieslist.db.MoviesRepository

class MoviesListViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private val moviesLoader = MoviesLoader()
    private val _mutableMoviesList = MutableLiveData<List<Movie>>(emptyList())
    private val _isLoading = MutableLiveData<Boolean>()

    val moviesList: LiveData<List<Movie>> get() = _mutableMoviesList
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        updateData()
    }

    /*
    private fun saveData(movies: List<Movie>) {
        viewModelScope.launch {
            moviesRepository.clearDatabase()
            moviesRepository.addAllMovies(movies)
        }
    }
    */

    private fun updateData() {
        viewModelScope.launch {
            _isLoading.value = true

            _mutableMoviesList.value = moviesRepository.getAllMovies()

            val newMoviesList = moviesLoader.getMovies()
            _mutableMoviesList.value = newMoviesList

            _isLoading.value = false

            //saveData(newMoviesList)
        }
    }
}