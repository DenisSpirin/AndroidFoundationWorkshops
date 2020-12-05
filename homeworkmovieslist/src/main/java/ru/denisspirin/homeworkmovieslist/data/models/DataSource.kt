package ru.denisspirin.homeworkmovieslist.data.models

class DataSource {
    fun getMovies(): List<Movie> {
        return listOf(
            Movie("image_avengers_end_game_card", 13, "Action, Adventure, Drama", 125, "Avangers: End Game", 137),
            Movie("image_tenet_card",16, "Action, Sci-Fi, Thriller", 98, "Tenet", 97),
            Movie("image_black_window_card",13, "Action, Adventure, Sci-Fi", 38, "Black Window", 102),
            Movie("image_wonder_woman_1984_card",13, "Action, Adventure, Fantasy", 74, "Wonder Woman 1984", 120)
        )
    }

    fun getActors(): List<Actor> {
        return listOf(
            Actor("image_robert_downey_jr_actor", "Robert Downey Jr."),
            Actor("image_chris_evans_actor", "Chris Evans"),
            Actor("image_mark_ruffalo_actor", "Mark Ruffalo"),
            Actor("image_chris_hemsworth_actor", "Chris Hemsworth")
        )
    }
}