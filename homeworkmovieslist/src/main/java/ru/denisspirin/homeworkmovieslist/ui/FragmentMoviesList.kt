package ru.denisspirin.homeworkmovieslist.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import ru.denisspirin.homework3uicomponents2.R
import ru.denisspirin.homeworkmovieslist.adapters.MoviesAdapter
import ru.denisspirin.homeworkmovieslist.data.loadMovies
import ru.denisspirin.homeworkmovieslist.listeners.MoviesListItemClickListener

class FragmentMoviesList : Fragment() {

    var itemClickListener: MoviesListItemClickListener? = null
    private var recycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.rvMoviesList)
        recycler?.adapter = itemClickListener?.let { MoviesAdapter(it) }
        recycler?.addItemDecoration(
            MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.movie_card_item_margin))
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MoviesListItemClickListener) {
            itemClickListener = context
        }
    }

    override fun onDetach() {
        recycler = null
        super.onDetach()
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }

    private fun updateData() {
        (recycler?.adapter as? MoviesAdapter)?.apply {
            runBlocking(Dispatchers.IO) {
                bindMovies(loadMovies(activity?.baseContext!!))
            }
        }
    }
}