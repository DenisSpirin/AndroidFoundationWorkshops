package ru.denisspirin.homeworkmovieslist.work_manager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.runBlocking
import ru.denisspirin.homeworkmovieslist.data.MoviesLoader
import ru.denisspirin.homeworkmovieslist.db.MoviesRepository

class DBLoaderWorker(context: Context, params: WorkerParameters): Worker(context, params){
    override fun doWork(): Result {
        val moviesRepository = MoviesRepository(applicationContext)
        val moviesLoader = MoviesLoader()

        runBlocking {
            val moviesList = moviesLoader.getMovies()
            moviesRepository.clearDatabase()
            moviesRepository.addAllMovies(moviesList)
            moviesList.forEach {
                val detailMovie = moviesLoader.getMovieDetail(it.id)
                moviesRepository.addActorsForMovie(detailMovie.id, detailMovie.actors)
            }
        }
        return Result.success()
    }
}