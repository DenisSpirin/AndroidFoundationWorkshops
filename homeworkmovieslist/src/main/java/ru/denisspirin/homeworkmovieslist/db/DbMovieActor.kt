package ru.denisspirin.homeworkmovieslist.db

import androidx.room.*

@Entity(
    tableName = DbContract.MovieActor.TABLE_NAME,
    indices = [Index(DbContract.MovieActor.COLUMN_NAME_ID),
        Index(DbContract.MovieActor.COLUMN_NAME_ID_MOVIE),
        Index(DbContract.MovieActor.COLUMN_NAME_ID_ACTOR)
    ],
    foreignKeys = [ForeignKey(
        entity = DbMovie::class,
        parentColumns = arrayOf(DbContract.Movie.COLUMN_NAME_ID),
        childColumns = arrayOf(DbContract.MovieActor.COLUMN_NAME_ID_MOVIE),
        onDelete = ForeignKey.CASCADE),
        ForeignKey(
            entity = DbActor::class,
            parentColumns = arrayOf(DbContract.Actor.COLUMN_NAME_ID),
            childColumns = arrayOf(DbContract.MovieActor.COLUMN_NAME_ID_ACTOR),
            onDelete = ForeignKey.CASCADE)
    ]
)
data class DbMovieActor (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbContract.MovieActor.COLUMN_NAME_ID)
    val id: Long = 0,

    @ColumnInfo(name = DbContract.MovieActor.COLUMN_NAME_ID_MOVIE)
    val movieId: Long,

    @ColumnInfo(name = DbContract.MovieActor.COLUMN_NAME_ID_ACTOR)
    val actorId: Long
)