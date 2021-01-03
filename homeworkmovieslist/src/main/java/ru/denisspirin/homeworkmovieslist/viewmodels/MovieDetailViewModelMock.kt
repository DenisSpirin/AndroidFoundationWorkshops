package ru.denisspirin.homeworkmovieslist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.denisspirin.homeworkmovieslist.data.MoviesLoaderMock
import ru.denisspirin.homeworkmovieslist.data.models.Movie

class MovieDetailViewModelMock(
    private val moviesLoader: MoviesLoaderMock,
) : ViewModel() {

    private val _mutableMovie =  MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _mutableMovie

    fun updateData(movieId: Int?) {
        viewModelScope.launch {
            val loadedMovie = moviesLoader.getMovie(movieId!!)
            _mutableMovie.postValue(loadedMovie)
        }
    }

    /*
    fun incrementReview() {
        movie?.value?.voteCount?.inc();
    }
    */
}