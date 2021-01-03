package ru.denisspirin.homeworkmovieslist.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.denisspirin.homeworkmovieslist.data.models.mdb.*

interface MovieDBApiService {
    @GET("configuration")
    suspend fun getConfiguration(
        @Query("api_key") apiKey: String
    ): ConfigurationResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): MoviesResponse

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String
    ) : GenresResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ) : MovieDetailResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ) : MovieCreditsResponse
}