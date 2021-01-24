package ru.denisspirin.homeworkmovieslist.db

import android.provider.BaseColumns

object DbContract {
    const val DATABASE_NAME = "movies.db"

    object Genre {
        const val TABLE_NAME = "t_genre"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_MDB_ID = "mdb_id"
        const val COLUMN_NAME_NAME = "name"
    }

    object Actor {
        const val TABLE_NAME = "t_actor"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_MDB_ID = "mdb_id"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_PICTURE = "picture"
    }

    object Movie {
        const val TABLE_NAME = "t_movie"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_MDB_ID = "mdb_id"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_OVERVIEW = "overview"
        const val COLUMN_NAME_POSTER = "poster"
        const val COLUMN_NAME_BACKDROP = "backdrop"
        const val COLUMN_NAME_RATING = "rating"
        const val COLUMN_NAME_ADULT = "adult"
        const val COLUMN_NAME_RUNTIME = "runtime"
        const val COLUMN_NAME_VOTE_COUNT = "vote_count"
    }

    // many-to-many movies and genres
    object MovieGenre {
        const val TABLE_NAME = "t_movie_genre"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_ID_GENRE = "id_genre"
        const val COLUMN_NAME_ID_MOVIE = "id_movie"
    }

    // many-to-many movies and actors
    object MovieActor {
        const val TABLE_NAME = "t_movie_actor"

        const val COLUMN_NAME_ID = BaseColumns._ID
        const val COLUMN_NAME_ID_MOVIE = "id_movie"
        const val COLUMN_NAME_ID_ACTOR = "id_actor"
    }
}