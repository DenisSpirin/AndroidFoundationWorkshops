package ru.denisspirin.homeworkmovieslist.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.denisspirin.homework3uicomponents2.R
import ru.denisspirin.homeworkmovieslist.adapters.MoviesAdapter
import ru.denisspirin.homeworkmovieslist.data.models.Movie
import ru.denisspirin.homeworkmovieslist.listeners.MoviesListItemClickListener
import ru.denisspirin.homeworkmovieslist.ui.viewholders.MovieCardViewHolder
import ru.denisspirin.homeworkmovieslist.viewmodels.MoviesListViewModelMovieDB

class FragmentMoviesList : Fragment(), Observer<List<Movie>> {

    //private val viewModel: MoviesListViewModelMock by viewModels { MoviesListViewModelMockFactory() }
    private var viewModel: MoviesListViewModelMovieDB? = null
    private var itemClickListener: MoviesListItemClickListener? = null
    private var recycler: RecyclerView? = null
    private var progressBar: ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MoviesListViewModelMovieDB::class.java)
        viewModel?.moviesList?.observe(viewLifecycleOwner, this::onChanged)
        viewModel?.isLoading?.observe(viewLifecycleOwner, this::setLoading)

        recycler = view.findViewById(R.id.rvMoviesList)
        recycler?.addItemDecoration(
            MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.movie_card_item_margin))
        )
        recycler?.adapter = itemClickListener?.let { MoviesAdapter(it) }

        progressBar = view.findViewById(R.id.progressBar)
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
        viewModel?.updateData()
    }

    override fun onChanged(movies: List<Movie>?) {
        (recycler?.adapter as? MoviesAdapter)?.bindMovies(movies!!)
    }

    fun setLoading(isLoading: Boolean) {
        when (isLoading) {
            true -> progressBar?.setVisibility(View.VISIBLE)
            false -> progressBar?.setVisibility(View.INVISIBLE)
        }
    }
}