package ru.denisspirin.homeworkmovieslist.db

import androidx.room.*

@Entity(
    tableName = DbContract.MovieGenre.TABLE_NAME,
    indices = [Index(DbContract.MovieGenre.COLUMN_NAME_ID),
        Index(DbContract.MovieGenre.COLUMN_NAME_ID_GENRE),
        Index(DbContract.MovieGenre.COLUMN_NAME_ID_MOVIE),
    ],
    foreignKeys = [ForeignKey(
        entity = DbMovie::class,
        parentColumns = arrayOf(DbContract.Movie.COLUMN_NAME_ID),
        childColumns = arrayOf(DbContract.MovieGenre.COLUMN_NAME_ID_MOVIE),
        onDelete = ForeignKey.CASCADE),
        ForeignKey(
            entity = DbGenre::class,
            parentColumns = arrayOf(DbContract.Genre.COLUMN_NAME_ID),
            childColumns = arrayOf(DbContract.MovieGenre.COLUMN_NAME_ID_GENRE),
            onDelete = ForeignKey.CASCADE)
    ]
)
data class DbMovieGenre (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbContract.MovieGenre.COLUMN_NAME_ID)
    val id: Long = 0,

    @ColumnInfo(name = DbContract.MovieGenre.COLUMN_NAME_ID_MOVIE)
    val movieId: Long,

    @ColumnInfo(name = DbContract.MovieGenre.COLUMN_NAME_ID_GENRE)
    val genreId: Long
)