package ru.denisspirin.homeworkmovieslist.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.denisspirin.homeworkmovieslist.db.DbGenre
import ru.denisspirin.homeworkmovieslist.db.DbMovieGenre

@Dao
interface GenreDao {

    @Query("SELECT g.* FROM t_genre g JOIN t_movie_genre mg ON g._id = mg.id_genre WHERE mg.id_movie = :movieId ORDER BY g.name")
    fun getByMovieId(movieId: Long): List<DbGenre>

    @Query("SELECT _id FROM t_genre WHERE mdb_id = :mdbId AND name = :name")
    fun getGenreIdByMDBIdAndName(mdbId: Int, name: String): Long?

    @Insert
    fun insert(dbGenre: DbGenre): Long

    @Insert
    fun insertMovieGenre(dbMovieGenre: DbMovieGenre): Long

    @Query("DELETE FROM t_genre")
    fun deleteAll()
}