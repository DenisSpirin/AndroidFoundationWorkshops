package ru.denisspirin.homeworkmovieslist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.denisspirin.homeworkmovieslist.db.dao.ActorDao
import ru.denisspirin.homeworkmovieslist.db.dao.GenreDao
import ru.denisspirin.homeworkmovieslist.db.dao.MovieDao

@Database(entities = [DbGenre::class, DbMovie::class, DbActor::class, DbMovieGenre::class, DbMovieActor::class], version = 6)
abstract class MoviesDatabase : RoomDatabase() {

    abstract val genreDao: GenreDao
    abstract val movieDao: MovieDao
    abstract val actorDao: ActorDao

    companion object {
        fun create(applicationContext: Context): MoviesDatabase = Room.databaseBuilder(
            applicationContext,
            MoviesDatabase::class.java,
            DbContract.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}