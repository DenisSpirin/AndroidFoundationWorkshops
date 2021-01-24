package ru.denisspirin.homeworkmovieslist.db

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = DbContract.Actor.TABLE_NAME,
    indices = [Index(DbContract.Actor.COLUMN_NAME_ID),
        Index(DbContract.Actor.COLUMN_NAME_MDB_ID)
    ]
)
data class DbActor (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbContract.Actor.COLUMN_NAME_ID)
    val id: Long = 0,

    @ColumnInfo(name = DbContract.Actor.COLUMN_NAME_MDB_ID)
    val mdb_id: Int,

    @ColumnInfo(name = DbContract.Actor.COLUMN_NAME_NAME)
    val name: String,

    @ColumnInfo(name = DbContract.Actor.COLUMN_NAME_PICTURE)
    val picture: String,
)