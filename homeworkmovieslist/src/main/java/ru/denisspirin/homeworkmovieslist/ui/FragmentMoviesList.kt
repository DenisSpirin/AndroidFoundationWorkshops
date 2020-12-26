package ru.denisspirin.homeworkmovieslist.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import ru.denisspirin.homework3uicomponents2.R
import ru.denisspirin.homeworkmovieslist.viewmodels.MoviesListViewModelFactory
import ru.denisspirin.homeworkmovieslist.adapters.MoviesAdapter
import ru.denisspirin.homeworkmovieslist.data.models.Movie
import ru.denisspirin.homeworkmovieslist.listeners.MoviesListItemClickListener
import ru.denisspirin.homeworkmovieslist.viewmodels.MoviesListViewModel

class FragmentMoviesList : Fragment(), Observer<List<Movie>> {

    private val viewModel: MoviesListViewModel by viewModels { MoviesListViewModelFactory() }
    private var itemClickListener: MoviesListItemClickListener? = null
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

        viewModel.moviesList.observe(viewLifecycleOwner, this::onChanged)

        recycler = view.findViewById(R.id.rvMoviesList)
        recycler?.addItemDecoration(
            MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.movie_card_item_margin))
        )
        recycler?.adapter = itemClickListener?.let { MoviesAdapter(it) }
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
        viewModel.updateData()
    }

    override fun onChanged(movies: List<Movie>?) {
        (recycler?.adapter as? MoviesAdapter)?.bindMovies(movies!!)
    }
}