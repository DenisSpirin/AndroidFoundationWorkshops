package ru.denisspirin.homeworkmovieslist.data.models.mdb

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailResponse (
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    //@SerialName("belongs_to_collection")
    //val belongsToCollection: BelongsToCollection? = null,
    @SerialName("budget")
    val budget: Long,
    @SerialName("genres")
    val genres: List<Genre>,
    @SerialName("homepage")
    val homepage: String,
    @SerialName("id")
    val id: Int,
    @SerialName("imdb_id")
    val imdbID: String?,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountry>,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("revenue")
    val revenue: Long,
    @SerialName("runtime")
    val runtime: Int,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    @SerialName("status")
    val status: String,
    @SerialName("tagline")
    val tagline: String,
    @SerialName("title")
    val title: String,
    @SerialName("video")
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Float,
    @SerialName("vote_count")
    val voteCount: Int
)

/*
@Serializable
data class BelongsToCollection (
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("backdrop_path")
    val backdropPath: String
)
*/

@Serializable
data class ProductionCompany (
    @SerialName("id")
    val id: Long,
    @SerialName("logo_path")
    val logoPath: String? = null,
    @SerialName("name")
    val name: String,
    @SerialName("origin_country")
    val originCountry: String
)

@Serializable
data class ProductionCountry (
    @SerialName("iso_3166_1")
    val iso3166_1: String,
    @SerialName("name")
    val name: String
)

@Serializable
data class SpokenLanguage (
    @SerialName("english_name")
    val englishName: String,
    @SerialName("iso_639_1")
    val iso639_1: String,
    @SerialName("name")
    val name: String
)