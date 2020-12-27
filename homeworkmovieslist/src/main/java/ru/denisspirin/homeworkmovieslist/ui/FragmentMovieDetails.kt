package ru.denisspirin.homeworkmovieslist.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.denisspirin.homework3uicomponents2.R
import ru.denisspirin.homework3uicomponents2.R.id.tvGenre
import ru.denisspirin.homeworkmovieslist.adapters.ActorsAdapter
import ru.denisspirin.homeworkmovieslist.data.models.Movie
import ru.denisspirin.homeworkmovieslist.listeners.MovieDetailsBackClickListener
import ru.denisspirin.homeworkmovieslist.ui.viewholders.MovieCardViewHolder
import ru.denisspirin.homeworkmovieslist.viewmodels.MovieDetailViewModel
import ru.denisspirin.homeworkmovieslist.viewmodels.MovieDetailViewModelFactory

class FragmentMovieDetails : Fragment(), Observer<Movie> {
    private val viewModel: MovieDetailViewModel by viewModels { MovieDetailViewModelFactory() }
    private var clickListener: MovieDetailsBackClickListener? = null
    private var recycler: RecyclerView? = null
    //private var movie: Movie? = null
    private var movieId: Int? = null
    private var ivMask: ImageView? = null
    private var tvCast: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.rvActorsList)
        ivMask = view.findViewById(R.id.ivMask)
        tvCast = view.findViewById(R.id.tvCast)

        viewModel.movie.observe(viewLifecycleOwner, this::onChanged)

        recycler?.adapter = ActorsAdapter()
        movieId = arguments?.getInt("movie")
    }

    override fun onDetach() {
        recycler = null
        clickListener = null
        super.onDetach()
    }

    override fun onStart() {
        super.onStart()

        view?.findViewById<TextView>(R.id.tvBack)?.setOnClickListener { clickListener?.goBack()}
        view?.findViewById<TextView>(R.id.tvBackDir)?.setOnClickListener { clickListener?.goBack() }

        viewModel.updateData(movieId)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MovieDetailsBackClickListener) {
            clickListener = context
        }
    }

    private fun updateData(movie: Movie?) {
        (recycler?.adapter as? ActorsAdapter)?.apply {
            bindActors(movie?.actors!!)
        }

        view?.findViewById<TextView>(R.id.tvTitile)?.text = movie?.title
        view?.findViewById<TextView>(R.id.tvCountReview)?.text = context?.getString(
            R.string.count_review,
            movie?.voteCount
        )
        view?.findViewById<TextView>(R.id.tvStorylineBody)?.text = movie?.overview

        val tvAgeRestriction: TextView? = view?.findViewById(R.id.tvAge)
        when (movie?.adult) {
            true -> tvAgeRestriction?.text = context?.getString(
                R.string.age_restriction_t,
                MovieCardViewHolder.ADULT_AGE
            )
            false -> tvAgeRestriction?.text = context?.getString(
                R.string.age_restriction_t,
                MovieCardViewHolder.OTHER_AGE
            )
        }

        view?.findViewById<TextView>(tvGenre)?.text = movie?.genres?.joinToString(
            separator = ", ",
            transform = { it -> it.name })
        view?.findViewById<RatingBar>(R.id.rbMovieDetails)?.rating = movie?.ratings?.div(2)!!

        tvCast?.isVisible = movie.actors.isNotEmpty()

        Glide.with(context)
            .load(movie.backdrop)
            .into(ivMask)
    }

    override fun onChanged(movie: Movie?) {
        updateData(movie)
    }

    companion object {
        fun newInstance(movieId: Int): FragmentMovieDetails {
            val fragment = FragmentMovieDetails()
            val bundle = Bundle()
            bundle.putInt("movie", movieId)
            fragment.arguments = bundle
            return fragment
        }
    }
}