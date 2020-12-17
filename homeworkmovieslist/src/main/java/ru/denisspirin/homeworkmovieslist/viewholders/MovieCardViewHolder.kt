package ru.denisspirin.homeworkmovieslist.viewholders

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.denisspirin.homework3uicomponents2.R
import ru.denisspirin.homeworkmovieslist.data.models.Movie

class MovieCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // save current context
    private val context: Context = view.context

    // find components
    private val ivMovie: ImageView = view.findViewById(R.id.ivMovie)
    private val ivLike: ImageView = view.findViewById(R.id.ivLike)
    private val tvAgeRestriction: TextView = view.findViewById(R.id.tvAgeRestriction)
    private val tvGenre: TextView = view.findViewById(R.id.tvGenre)
    private val tvCountReview: TextView = view.findViewById(R.id.tvCountReview)
    private val tvMovieTitle: TextView = view.findViewById(R.id.tvMovieTitle)
    private val tvDuration: TextView = view.findViewById(R.id.tvDuration)
    private val rbMovieCard: RatingBar = view.findViewById(R.id.rbMovieCard)

    fun onBind(movie: Movie) {
        Glide.with(context)
            .load(movie.poster)
            .into(ivMovie)

        tvMovieTitle.text = movie.title
        tvGenre.text = movie.genres.joinToString(separator = ", ") { it.name }
        tvCountReview.text = context.getString(R.string.count_review, movie.voteCount)
        tvDuration.text = context.getString(R.string.duration, movie.runtime)

        when (movie.adult) {
            true ->  tvAgeRestriction.text = context.getString(R.string.age_restriction_t, ADULT_AGE)
            false ->  tvAgeRestriction.text = context.getString(R.string.age_restriction_t, OTHER_AGE)
        }

        ivLike.setImageResource(R.drawable.ic_like)
        rbMovieCard.rating = movie.ratings.div(2)
        /*
        when (movie.isLiked) {
            true -> ivLike.setImageResource(R.drawable.ic_like_clicked)
            else -> ivLike.setImageResource(R.drawable.ic_like)
        }
        */
    }

    companion object {
        const val ADULT_AGE = 16
        const val OTHER_AGE = 13
    }
}