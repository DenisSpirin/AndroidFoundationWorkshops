package ru.denisspirin.homeworkmovieslist.db

import androidx.room.*

@Entity(
    tableName = DbContract.Movie.TABLE_NAME,
    indices = [Index(DbContract.Movie.COLUMN_NAME_ID),
        Index(DbContract.Movie.COLUMN_NAME_MDB_ID)
    ]
)
data class DbMovie (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbContract.Movie.COLUMN_NAME_ID)
    val id: Long = 0,

    @ColumnInfo(name = DbContract.Movie.COLUMN_NAME_MDB_ID)
    val mdb_id: Int,

    @ColumnInfo(name = DbContract.Movie.COLUMN_NAME_TITLE)
    val title: String,

    @ColumnInfo(name = DbContract.Movie.COLUMN_NAME_OVERVIEW)
    val overview: String,

    @ColumnInfo(name = DbContract.Movie.COLUMN_NAME_POSTER)
    val poster: String,

    @ColumnInfo(name = DbContract.Movie.COLUMN_NAME_BACKDROP)
    val backdrop: String,

    @ColumnInfo(name = DbContract.Movie.COLUMN_NAME_RATING)
    val rating: Float,

    @ColumnInfo(name = DbContract.Movie.COLUMN_NAME_ADULT)
    val adult: Boolean,

    @ColumnInfo(name = DbContract.Movie.COLUMN_NAME_RUNTIME)
    val runtime: Int,

    @ColumnInfo(name = DbContract.Movie.COLUMN_NAME_VOTE_COUNT)
    var voteCount: Int
)