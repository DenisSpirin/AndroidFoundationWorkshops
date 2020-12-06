package ru.denisspirin.homeworkmovieslist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.denisspirin.homework3uicomponents2.R
import ru.denisspirin.homeworkmovieslist.MoviesListItemClickListener
import ru.denisspirin.homeworkmovieslist.data.models.Movie
import ru.denisspirin.homeworkmovieslist.viewholders.MovieCardViewHolder

class MoviesAdapter(
    private val itemClickListener: MoviesListItemClickListener
) : RecyclerView.Adapter<MovieCardViewHolder>() {

    private var movies = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardViewHolder {
        return MovieCardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieCardViewHolder, position: Int) {
        holder.onBind(movies[position])
        // todo setOnClickListener
        holder.itemView.setOnClickListener { itemClickListener.onClick(movies[position]) }
    }

    fun bindMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

}