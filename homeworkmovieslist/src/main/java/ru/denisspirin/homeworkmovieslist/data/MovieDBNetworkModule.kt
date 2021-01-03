package ru.denisspirin.homeworkmovieslist.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import ru.denisspirin.homeworkmovieslist.data.models.mdb.*

class MovieDBNetworkModule {
    private val baseUrl = "https://api.themoviedb.org/3/"
    private val apiKey = "12849f7d791639a266d8f229d6478c6d"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val contentType = "application/json".toMediaType()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()

    private val movieDBApiService: MovieDBApiService = retrofit.create()

    suspend fun getConfiguration(): ConfigurationResponse {
        return movieDBApiService.getConfiguration(apiKey)
    }

    suspend fun getMovies(): MoviesResponse {
        return movieDBApiService.getPopularMovies(apiKey)
    }

    suspend fun getGenres(): GenresResponse {
        return movieDBApiService.getGenres(apiKey)
    }

    suspend fun getMovieDetails(movieId: Int): MovieDetailResponse {
        return movieDBApiService.getMovieDetails(movieId, apiKey)
    }

    suspend fun getMovieCredits(movieId: Int): MovieCreditsResponse {
        return movieDBApiService.getMovieCredits(movieId, apiKey)
    }
}