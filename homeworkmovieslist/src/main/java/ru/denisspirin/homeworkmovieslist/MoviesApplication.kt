package ru.denisspirin.homeworkmovieslist

import android.app.Application
import ru.denisspirin.homeworkmovieslist.db.MoviesDatabase
import ru.denisspirin.homeworkmovieslist.db.MoviesDatabase_Impl

class MoviesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MoviesApplication
            private set

        //var moviesDb = MoviesDatabase.create(instance.applicationContext)
    }
}