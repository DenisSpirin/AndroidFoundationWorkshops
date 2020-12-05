package ru.denisspirin.homeworkmovieslist

import ru.denisspirin.homeworkmovieslist.data.models.Movie

interface MoviesListItemClickListener {
    fun onClick(movie: Movie)
}