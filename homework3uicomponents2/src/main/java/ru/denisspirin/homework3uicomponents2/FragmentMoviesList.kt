package ru.denisspirin.homework3uicomponents2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class FragmentMoviesList : Fragment(), ClickListener {

    var movieCard: CardView? = null
    var fragmentMoviesDetails: FragmentMoviesDetails = FragmentMoviesDetails().apply { setClickListener(this@FragmentMoviesList) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieCard = view.findViewById<CardView>(R.id.cardView).apply {
            setOnClickListener {
                activity?.supportFragmentManager?.beginTransaction()
                        ?.add(R.id.main_container, fragmentMoviesDetails)
                        ?.addToBackStack(null)
                        ?.commit()
            }
        }

    }

    override fun goBack() {
        if (activity?.supportFragmentManager?.fragments?.size!! > 1) {
            val lastFragment = activity?.supportFragmentManager?.fragments?.last()
            activity?.supportFragmentManager?.beginTransaction()
                ?.apply {
                    remove(lastFragment!!)
                    commit()
                }
        }
    }
}