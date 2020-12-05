package ru.denisspirin.homeworkmovieslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.denisspirin.homework3uicomponents2.R
import ru.denisspirin.homeworkmovieslist.data.models.Movie

class MainActivity : AppCompatActivity(),
    MoviesDetailsBackClickListener,
    MoviesListItemClickListener
{
    private var fragmentMoviesList: FragmentMoviesList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            fragmentMoviesList = FragmentMoviesList()
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.main_container, fragmentMoviesList!!,
                    MOVIE_LIST_FRAGMENT_TAG
                )
                .commit()
        } else {
            fragmentMoviesList = supportFragmentManager.findFragmentByTag(MOVIE_LIST_FRAGMENT_TAG) as? FragmentMoviesList
        }
    }

    override fun goBack() {
        supportFragmentManager.popBackStack()
    }

    override fun onClick(movie: Movie) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, FragmentMoviesDetails(), FragmentMoviesDetails::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val MOVIE_LIST_FRAGMENT_TAG = "FragmentMoviesList"
        const val MOVIE_DETAILS_FRAGMENT_TAG = "FragmentMoviesDetails"
    }
}
