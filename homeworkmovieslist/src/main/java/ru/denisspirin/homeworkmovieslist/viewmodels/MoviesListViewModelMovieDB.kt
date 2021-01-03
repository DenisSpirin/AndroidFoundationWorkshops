package ru.denisspirin.homeworkmovieslist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.denisspirin.homeworkmovieslist.data.MoviesLoaderMovieDB
import ru.denisspirin.homeworkmovieslist.data.models.Movie

class MoviesListViewModelMovieDB : ViewModel() {
    private val moviesLoader = MoviesLoaderMovieDB()
    private val _mutableMoviesList = MutableLiveData<List<Movie>>(emptyList())
    private val _isLoading = MutableLiveData<Boolean>()

    val moviesList: LiveData<List<Movie>> get() = _mutableMoviesList
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun updateData() {
        viewModelScope.launch {
            _isLoading.value = true

            val newMoviesList = moviesLoader.getMovies()
            _mutableMoviesList.value = newMoviesList

            _isLoading.value = false
        }
    }
}