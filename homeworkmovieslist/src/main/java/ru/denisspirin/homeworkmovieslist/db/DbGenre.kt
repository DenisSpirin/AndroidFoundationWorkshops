package ru.denisspirin.homeworkmovieslist.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = DbContract.Genre.TABLE_NAME,
    indices = [Index(DbContract.Genre.COLUMN_NAME_ID),
        Index(DbContract.Genre.COLUMN_NAME_MDB_ID)
    ]
)
data class DbGenre (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbContract.Genre.COLUMN_NAME_ID)
    val id: Long = 0,

    @ColumnInfo(name = DbContract.Genre.COLUMN_NAME_MDB_ID)
    val mdb_id: Int,

    @ColumnInfo(name = DbContract.Genre.COLUMN_NAME_NAME)
    val name: String
)