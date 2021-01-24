package ru.denisspirin.homeworkmovieslist.db

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.denisspirin.homeworkmovieslist.data.models.Actor
import ru.denisspirin.homeworkmovieslist.data.models.Genre
import ru.denisspirin.homeworkmovieslist.data.models.Movie

class MoviesRepository(applicationContext: Context) {

    private val moviesDb = MoviesDatabase.create(applicationContext)

    private suspend fun deleteGenres() = withContext(Dispatchers.IO) {
        moviesDb.genreDao.deleteAll()
    }

    private suspend fun deleteActors() = withContext(Dispatchers.IO) {
        moviesDb.actorDao.deleteAll()
    }

    private suspend fun deleteMovies() = withContext(Dispatchers.IO) {
        moviesDb.movieDao.deleteAll()
    }

    private fun getGenresByMovieId(movieId: Long): List<DbGenre> {
        return moviesDb.genreDao.getByMovieId(movieId)
    }

    private fun getActorsByMovieId(movieId: Long): List<DbActor> {
        return moviesDb.actorDao.getByMovieId(movieId)
    }

    private fun addActorIfNotExist(dbActor: DbActor): Long {
        var actorId = moviesDb.actorDao.getActorIdByMDBIdAndName(dbActor.mdb_id, dbActor.name)
        if (actorId == null) {
            actorId = moviesDb.actorDao.insert(dbActor)
        }
        return actorId
    }

    private suspend fun addActorForMovie(dbMovieActor: DbMovieActor): Long = withContext(Dispatchers.IO) {
        moviesDb.actorDao.insertMovieActor(dbMovieActor)
    }

    suspend fun addActorsForMovie(movieMdbId: Int, actors: List<Actor>) = withContext(Dispatchers.IO) {
        moviesDb.actorDao.deleteAllForMovie(movieMdbId)
        actors.forEach {
            var actorId = moviesDb.actorDao.getActorIdByMDBIdAndName(it.id, it.name)
            if (actorId == null) {
                actorId = moviesDb.actorDao.insert(DbActor(mdb_id = it.id, name = it.name, picture = it.picture.toString()))
            }
            moviesDb.actorDao.insertMovieActor(
                DbMovieActor(
                    movieId = moviesDb.movieDao.getMovieByMdbId(movieMdbId).id,
                    actorId = actorId
                )
            )
        }
    }

    private fun addGenreIfNotExist(dbGenre: DbGenre): Long {
        var genreId = moviesDb.genreDao.getGenreIdByMDBIdAndName(dbGenre.mdb_id, dbGenre.name)
        if (genreId == null) {
            genreId = moviesDb.genreDao.insert(dbGenre)
        }
        return genreId
    }

    private fun addGenreForMovie(dbMovieGenre: DbMovieGenre): Long {
        return moviesDb.genreDao.insertMovieGenre(dbMovieGenre)
    }

    private fun addMovie(movie: DbMovie): Long {
        return moviesDb.movieDao.insertMovie(movie)
    }

    suspend fun getMovie(mdbId: Int) = withContext(Dispatchers.IO) {
        toModel(moviesDb.movieDao.getMovieByMdbId(mdbId))
    }

    suspend fun getAllMovies(): List<Movie> = withContext(Dispatchers.IO) {
        moviesDb.movieDao.getAllMovies().map { toModel(it) }
    }

    suspend fun addAllMovies(movies: List<Movie>) = withContext(Dispatchers.IO) {
        movies.forEach {
            val movieId = addMovie(
                DbMovie(
                    mdb_id = it.id,
                    title = it.title,
                    overview = it.overview,
                    poster = it.poster,
                    backdrop = it.backdrop,
                    rating = it.ratings,
                    adult = it.adult,
                    runtime = it.runtime,
                    voteCount = it.voteCount
                )
            )
            it.actors.forEach {
                val actorId = addActorIfNotExist(
                    DbActor(mdb_id = it.id, name = it.name, picture = it.picture.toString())
                )
                addActorForMovie(DbMovieActor(movieId = movieId, actorId = actorId))
            }
            it.genres.forEach {
                val genreId = addGenreIfNotExist(
                    DbGenre(mdb_id = it.id, name = it.name)
                )
                addGenreForMovie(DbMovieGenre(movieId = movieId, genreId = genreId))
            }
        }
    }

    suspend fun clearDatabase() = withContext(Dispatchers.IO) {
        deleteGenres()
        deleteActors()
        deleteMovies()
    }

    private fun toModel(dbMovie: DbMovie): Movie {
        return Movie(
            id = dbMovie.mdb_id,
            title = dbMovie.title,
            overview = dbMovie.overview,
            poster = dbMovie.poster,
            backdrop = dbMovie.backdrop,
            ratings = dbMovie.rating,
            adult = dbMovie.adult,
            runtime = dbMovie.runtime,
            voteCount = dbMovie.voteCount,
            genres = getGenresByMovieId(dbMovie.id).map { Genre(id = it.mdb_id, name = it.name) },
            actors = getActorsByMovieId(dbMovie.id).map { Actor(id = it.mdb_id, name = it.name, picture = it.picture) }
        )
    }
}