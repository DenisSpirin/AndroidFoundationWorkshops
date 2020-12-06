package ru.denisspirin.homeworkmovieslist.viewholders

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.denisspirin.homework3uicomponents2.R
import ru.denisspirin.homeworkmovieslist.data.models.Movie

class MovieCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // save current context
    private val context: Context = view.context

    // find components
    private val ivMovie: ImageView = view.findViewById(R.id.ivMovie)
    private val tvAgeRestriction: TextView = view.findViewById(R.id.tvAgeRestriction)
    private val tvGenre: TextView = view.findViewById(R.id.tvGenre)
    private val tvCountReview: TextView = view.findViewById(R.id.tvCountReview)
    private val tvMovieTitle: TextView = view.findViewById(R.id.tvMovieTitle)
    private val tvDuration: TextView = view.findViewById(R.id.tvDuration)
    private val ivLike: ImageView = view.findViewById(R.id.ivLike)
    // todo сделать нормальный рейтнг бар
    private val ratingMap: HashMap<Int, ImageView> = hashMapOf(
            1 to view.findViewById(R.id.ivStar1),
            2 to view.findViewById(R.id.ivStar2),
            3 to view.findViewById(R.id.ivStar3),
            4 to view.findViewById(R.id.ivStar4),
            5 to view.findViewById(R.id.ivStar5))

    fun onBind(movie: Movie) {
        ivMovie.setImageResource(context.resources.getIdentifier(movie.image, "drawable", context.packageName))
        tvAgeRestriction.text = context.getString(R.string.age_restriction_t, movie.ageRestriction)
        tvGenre.text = movie.genre
        tvCountReview.text = context.getString(R.string.count_review, movie.countReview)
        tvMovieTitle.text = movie.title
        tvDuration.text = context.getString(R.string.duration, movie.duration)

        when (movie.isLiked) {
            true -> ivLike.setImageResource(R.drawable.ic_like_clicked)
            else -> ivLike.setImageResource(R.drawable.ic_like)
        }

        // todo сделать нормальный рейтнг бар
        /*
        ratingMap.forEach {
            k, v ->
                if (movie.rating >= k)
                    v.setImageResource(R.drawable.ic_star_icon)
                else
                    v.setImageResource(R.drawable.ic_star_icon_gray)
        }
        */
        for ((k, v) in ratingMap) {
            if (movie.rating >= k)
                v.setImageResource(R.drawable.ic_star_icon)
            else
                v.setImageResource(R.drawable.ic_star_icon_gray)
        }
    }

}