package ru.denisspirin.homeworkmovieslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.denisspirin.homework3uicomponents2.R
import ru.denisspirin.homeworkmovieslist.listeners.MovieDetailsBackClickListener
import ru.denisspirin.homeworkmovieslist.listeners.MoviesListItemClickListener
import ru.denisspirin.homeworkmovieslist.ui.FragmentMovieDetails
import ru.denisspirin.homeworkmovieslist.ui.FragmentMoviesList

class MainActivity : AppCompatActivity(),
        MovieDetailsBackClickListener,
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

    override fun onClick(movieId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, FragmentMovieDetails.newInstance(movieId), FragmentMovieDetails::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val MOVIE_LIST_FRAGMENT_TAG = "FragmentMoviesList"
    }
}
