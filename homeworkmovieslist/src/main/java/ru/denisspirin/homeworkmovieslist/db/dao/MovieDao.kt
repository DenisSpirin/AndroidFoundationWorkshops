package ru.denisspirin.homeworkmovieslist.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.denisspirin.homeworkmovieslist.db.DbMovie

@Dao
interface MovieDao {

    @Query("SELECT * FROM t_movie")
    fun getAllMovies(): List<DbMovie>

    @Query("SELECT * FROM t_movie WHERE mdb_id = :mdb_id")
    fun getMovieByMdbId(mdb_id: Int): DbMovie

    @Insert
    fun insertMovies(dbMovies: List<DbMovie>)

    @Insert
    fun insertMovie(dbMovies: DbMovie): Long

    @Query("DELETE FROM t_movie")
    fun deleteAll()
}