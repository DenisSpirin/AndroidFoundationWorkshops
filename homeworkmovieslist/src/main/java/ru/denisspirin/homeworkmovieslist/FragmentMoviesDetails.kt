package ru.denisspirin.homeworkmovieslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ru.denisspirin.homework3uicomponents2.R
import ru.denisspirin.homeworkmovieslist.data.models.DataSource

class FragmentMoviesDetails : Fragment() {

    private var clickListener: MoviesDetailsBackClickListener? = null
    private var recycler: RecyclerView? = null

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
        recycler?.adapter = ActorsAdapter()
    }

    fun setClickListener(listener: MoviesDetailsBackClickListener?) {
        clickListener = listener
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

        updateData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MoviesDetailsBackClickListener) {
            clickListener = context
        }
    }

    private fun updateData() {
        (recycler?.adapter as? ActorsAdapter)?.apply {
            bindActors(DataSource().getActors())
        }
    }
}