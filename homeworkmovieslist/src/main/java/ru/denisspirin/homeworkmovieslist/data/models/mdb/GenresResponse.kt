package ru.denisspirin.homeworkmovieslist.data.models.mdb

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresResponse (
    @SerialName("genres")
    val genres: List<Genre>
)

@Serializable
data class Genre (
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)
