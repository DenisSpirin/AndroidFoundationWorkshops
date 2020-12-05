package ru.denisspirin.homeworkmovieslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ru.denisspirin.homework3uicomponents2.R
import ru.denisspirin.homeworkmovieslist.data.models.Movie
import ru.denisspirin.homeworkmovieslist.data.models.DataSource

class FragmentMoviesList : Fragment() {

    var movieCard: CardView? = null
    var itemClickListener: MoviesListItemClickListener? = null
    private var recycler: RecyclerView? = null
    //var fragmentMoviesDetails: FragmentMoviesDetails = FragmentMoviesDetails().apply { setClickListener(this@FragmentMoviesList) }
    var fragmentMoviesDetails: FragmentMoviesDetails? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
            fragmentMoviesDetails = FragmentMoviesDetails()
                .apply { setClickListener(this@FragmentMoviesList) }
            movieCard = view.findViewById<CardView>(R.id.cardView).apply {
                setOnClickListener {
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.add(R.id.main_container, fragmentMoviesDetails!!)
                        ?.addToBackStack(null)
                        //?.disallowAddToBackStack()
                        ?.commit()
                }
            }
        */
        recycler = view.findViewById(R.id.rvMoviesList)
        recycler?.adapter = itemClickListener?.let { MoviesAdapter(it) }
    }

    /*
    override fun goBack() {
        if (activity?.supportFragmentManager?.fragments?.size!! > 1) {
            val lastFragment = activity?.supportFragmentManager?.fragments?.last()
            activity?.supportFragmentManager?.beginTransaction()
                ?.apply {
                    remove(lastFragment!!)
                    commit()
                }
            //activity?.supportFragmentManager?.popBackStack()
        }
    }
    */

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
            bindMovies(DataSource().getMovies())
        }
    }

    /*
    private fun doOnClick(movie: Movie) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.main_container, fragmentMoviesDetails!!)
            ?.addToBackStack(null)
            //?.disallowAddToBackStack()
            ?.commit()
    }
    */

    /*
    private val itemClickListener = object : MoviesListItemClickListener {
        override fun onClick(movie: Movie) {
            doOnClick(movie)
        }
    }
    */
}