package ru.denisspirin.homeworkmovieslist.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.denisspirin.homeworkmovieslist.db.DbActor
import ru.denisspirin.homeworkmovieslist.db.DbMovie
import ru.denisspirin.homeworkmovieslist.db.DbMovieActor

@Dao
interface ActorDao {

    @Query("SELECT a.* FROM t_actor a JOIN t_movie_actor ma ON a._id = ma.id_actor WHERE ma.id_movie = :movieId ORDER BY a.name")
    fun getByMovieId(movieId: Long): List<DbActor>

    @Query("SELECT _id FROM t_actor WHERE mdb_id = :mdbId AND name = :name")
    fun getActorIdByMDBIdAndName(mdbId: Int, name: String): Long?

    @Insert
    fun insert(dbActor: DbActor): Long

    @Insert
    fun insertMovieActor(dbMovieActor: DbMovieActor): Long

    @Query("DELETE FROM t_actor")
    fun deleteAll()

    @Query("DELETE FROM t_movie_actor WHERE id_movie IN (SELECT m._id FROM t_movie m WHERE m.mdb_id = :movieMdbId)")
    fun deleteAllForMovie(movieMdbId: Int)
}