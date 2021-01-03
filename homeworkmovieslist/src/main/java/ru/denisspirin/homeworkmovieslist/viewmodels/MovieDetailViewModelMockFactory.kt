package ru.denisspirin.homeworkmovieslist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.denisspirin.homeworkmovieslist.MoviesApplication
import ru.denisspirin.homeworkmovieslist.data.MoviesLoaderMock

class MovieDetailViewModelMockFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieDetailViewModelMock::class.java -> MovieDetailViewModelMock(MoviesLoaderMock(MoviesApplication.instance.applicationContext))
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}