package ru.denisspirin.homeworkmovieslist.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    val id: Int,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val ratings: Float,
    val adult: Boolean,
    val runtime: Int,
    val voteCount: Int,
    val genres: List<Genre>,
    val actors: List<Actor>
) : Parcelable