package ru.denisspirin.homeworkmovieslist.data.models

data class Movie (
    val image: String,
    val ageRestriction: Int,
    val genre: String,
    val countReview: Int,
    val title: String,
    val duration: Int,
    val isLiked: Boolean
)