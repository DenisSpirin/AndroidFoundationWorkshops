package ru.denisspirin.homeworkmovieslist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.denisspirin.homeworkmovieslist.data.MoviesLoaderMock
import ru.denisspirin.homeworkmovieslist.data.MoviesLoaderMovieDB
import ru.denisspirin.homeworkmovieslist.data.models.Movie

class MoviesListViewModelMock (
    private val moviesLoader: MoviesLoaderMock
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