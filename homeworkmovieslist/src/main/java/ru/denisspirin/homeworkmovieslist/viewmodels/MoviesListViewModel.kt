package ru.denisspirin.homeworkmovieslist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.denisspirin.homeworkmovieslist.data.MoviesLoader
import ru.denisspirin.homeworkmovieslist.data.models.Movie

class MoviesListViewModel (
    private val moviesLoader: MoviesLoader
    ) : ViewModel() {

    private val _mutableMoviesList =  MutableLiveData<List<Movie>>(emptyList())
    val moviesList: LiveData<List<Movie>> get() = _mutableMoviesList

    fun updateData() {
        viewModelScope.launch {
            val newMoviesList = moviesLoader.getMovies()
            _mutableMoviesList.value = newMoviesList
        }
    }
}