package ru.denisspirin.homeworkmovieslist.data.models.mdb

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCreditsResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("cast")
    val cast: List<Person>,
    @SerialName("crew")
    val crew: List<Person>
)

@Serializable
data class Person (
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("gender")
    val gender: Long,
    @SerialName("id")
    val id: Int,
    @SerialName("known_for_department")
    val knownForDepartment: String,
    @SerialName("name")
    val name: String,
    @SerialName("original_name")
    val originalName: String,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("profile_path")
    val profilePath: String? = null,
    @SerialName("cast_id")
    val castID: Long? = null,
    @SerialName("character")
    val character: String? = null,
    @SerialName("credit_id")
    val creditID: String,
    @SerialName("order")
    val order: Long? = null,
    @SerialName("department")
    val department: String? = null,
    @SerialName("job")
    val job: String? = null
)