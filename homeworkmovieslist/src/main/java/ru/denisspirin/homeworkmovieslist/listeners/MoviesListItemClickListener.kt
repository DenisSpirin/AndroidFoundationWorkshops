package ru.denisspirin.homeworkmovieslist.listeners

import ru.denisspirin.homeworkmovieslist.data.models.Movie

interface MoviesListItemClickListener {
    fun onClick(movieId: Int)
}