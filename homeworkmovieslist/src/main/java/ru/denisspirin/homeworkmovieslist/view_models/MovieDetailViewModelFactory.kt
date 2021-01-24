package ru.denisspirin.homeworkmovieslist.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.denisspirin.homeworkmovieslist.MoviesApplication
import ru.denisspirin.homeworkmovieslist.db.MoviesRepository

class MovieDetailViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieDetailViewModel::class.java -> MovieDetailViewModel(
            MoviesRepository(MoviesApplication.instance.applicationContext)
        )
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}