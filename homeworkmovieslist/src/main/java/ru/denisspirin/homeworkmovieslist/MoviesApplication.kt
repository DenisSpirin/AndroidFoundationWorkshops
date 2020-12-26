package ru.denisspirin.homeworkmovieslist

import android.app.Application

class MoviesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MoviesApplication
            private set
    }
}