package ru.denisspirin.homeworkmovieslist.work_manager

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import java.util.concurrent.TimeUnit

class DBLoaderWorkRepository {
    private val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
    val loaderWork = PeriodicWorkRequest.Builder(DBLoaderWorker::class.java, 8, TimeUnit.HOURS)
        .setConstraints(constraints)
        .addTag(DBLoaderWorker::class.java.toString())
        .build()
}