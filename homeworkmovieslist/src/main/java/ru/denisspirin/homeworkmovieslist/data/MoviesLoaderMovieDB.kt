package ru.denisspirin.homeworkmovieslist.data

import kotlinx.serialization.ExperimentalSerializationApi
import ru.denisspirin.homeworkmovieslist.data.models.Actor
import ru.denisspirin.homeworkmovieslist.data.models.Genre
import ru.denisspirin.homeworkmovieslist.data.models.Movie
import ru.denisspirin.homeworkmovieslist.data.models.mdb.ConfigurationResponse
import ru.denisspirin.homeworkmovieslist.data.models.mdb.GenresResponse

class MoviesLoaderMovieDB {
    private val networkModule = MovieDBNetworkModule()
    private var configurationResponse: ConfigurationResponse? = null
    private var genresResponse: GenresResponse? = null
    private var genresMap: Map<Int, Genre>? = null

    private suspend fun loadConfiguration() {
        if (configurationResponse == null) {
            configurationResponse = networkModule.getConfiguration()
        }
    }

    private suspend fun loadDictionary() {
        if (genresResponse == null) {
            genresResponse = networkModule.getGenres()

            genresMap = genresResponse!!.genres
                .map { Genre(id = it.id, name = it.name) }
                .associateBy{ it.id }
        }


    }

    private fun getPosterUrl(posterPath: String): String {
        return configurationResponse?.images?.baseUrl +
                (if (configurationResponse?.images?.posterSizes?.contains("w342") == true)
                    {"w342"}
                else
                    {
                        configurationResponse?.images?.posterSizes?.get(0)}) +
                posterPath
    }

    private fun getBackdropUrl(backdropPath: String): String {

        return configurationResponse?.images?.baseUrl +
                (if (configurationResponse?.images?.backdropSizes?.contains("w300") == true)
                    {"w300"}
                else
                    {
                        configurationResponse?.images?.backdropSizes?.get(0)}) +
                backdropPath
    }

    private suspend fun getCast(movieId: Int): List<Actor> {
        val creditsResponse = networkModule.getMovieCredits(movieId)

        return creditsResponse.cast.map {
            Actor(
                id = it.id,
                name = it.name,
                picture = (if (it.profilePath != null) {getPosterUrl(it.profilePath.toString())} else {null})
            )
        }
    }

    suspend fun getMovieDetail(movieId: Int): Movie {
        // загружаем конфигурацию
        loadConfiguration()

        // загружаем справочоники
        loadDictionary()

        val movieDetailResponse = networkModule.getMovieDetails(movieId)
        return Movie(
            id = movieDetailResponse.id,
            title = movieDetailResponse.title,
            overview = movieDetailResponse.overview,
            poster = movieDetailResponse.posterPath,
            backdrop = getBackdropUrl(movieDetailResponse.backdropPath),
            ratings = movieDetailResponse.voteAverage,
            adult = movieDetailResponse.adult,
            runtime = movieDetailResponse.runtime,
            voteCount = movieDetailResponse.voteCount,
            genres = movieDetailResponse.genres.map { genre ->
                genresMap!![genre.id] ?: throw IllegalArgumentException("Genre not found")
            },
            actors = getCast(movieId)
        )
    }

    @Suppress("TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING")
    suspend fun getMovies(): List<Movie> {
        // загружаем конфигурацию
        loadConfiguration()

        // загружаем справочоники
        loadDictionary()

        val moviesResponse = networkModule.getMovies()
        return moviesResponse.results.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                poster = getPosterUrl(it.posterPath),
                backdrop =  getBackdropUrl(it.backdropPath),
                ratings = it.voteAverage,
                adult = it.adult,
                runtime = getMovieDetail(it.id).runtime,
                voteCount = it.voteCount,
                genres = it.genreIDS.map { genreId ->
                    genresMap!![genreId] ?: throw IllegalArgumentException("Genre not found")
                },
                actors = emptyList()
            )
        }
    }
}