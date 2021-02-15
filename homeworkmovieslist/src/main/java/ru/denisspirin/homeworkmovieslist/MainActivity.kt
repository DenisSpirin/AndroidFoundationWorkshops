package ru.denisspirin.homeworkmovieslist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.WorkManager
import ru.denisspirin.homework3uicomponents2.R
import ru.denisspirin.homeworkmovieslist.listeners.MovieDetailsBackClickListener
import ru.denisspirin.homeworkmovieslist.listeners.MoviesListItemClickListener
import ru.denisspirin.homeworkmovieslist.ui.FragmentMovieDetails
import ru.denisspirin.homeworkmovieslist.ui.FragmentMoviesList
import ru.denisspirin.homeworkmovieslist.work_manager.DBLoaderWorkRepository

class MainActivity : AppCompatActivity(),
        MovieDetailsBackClickListener,
        MoviesListItemClickListener
{
    private var fragmentMoviesList: FragmentMoviesList? = null
    private val workRepository = DBLoaderWorkRepository()

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
            intent?.let(::handleIntent)
        } else {
            fragmentMoviesList = supportFragmentManager.findFragmentByTag(MOVIE_LIST_FRAGMENT_TAG) as? FragmentMoviesList
        }

        WorkManager.getInstance(applicationContext).enqueue(workRepository.loaderWork)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            handleIntent(intent)
        }
    }

    private fun handleIntent(intent: Intent) {
        when (intent.action) {
            Intent.ACTION_VIEW -> {
                val movieId = intent.data?.lastPathSegment?.toIntOrNull()
                if (movieId != null) {
                    onClick(movieId)
                }
            }
        }
        /*
        when (intent.action) {
            // Invoked when a dynamic shortcut is clicked.
            Intent.ACTION_VIEW -> {
                val id = intent.data?.lastPathSegment?.toLongOrNull()
                if (id != null) {
                    openChat(id, null)
                }
            }
            // Invoked when a text is shared through Direct Share.
            Intent.ACTION_SEND -> {
                val shortcutId = intent.getStringExtra(Intent.EXTRA_SHORTCUT_ID)
                val text = intent.getStringExtra(Intent.EXTRA_TEXT)
                val contact = Contact.CONTACTS.find { it.shortcutId == shortcutId }
                if (contact != null) {
                    openChat(contact.id, text)
                }
            }
        }
        */
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
