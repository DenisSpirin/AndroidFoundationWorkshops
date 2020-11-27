package ru.denisspirin.homework3uicomponents2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentMoviesDetails : Fragment() {

    private var tvBack: TextView? = null
    private var tvBackDir: TextView? = null
    private var clickListener: ClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvBack = view.findViewById<TextView>(R.id.tvBack).apply { setOnClickListener {clickListener?.goBack()} }
        tvBackDir = view.findViewById<TextView>(R.id.tvBackDir).apply { setOnClickListener {clickListener?.goBack()} }
    }

    fun setClickListener(listener: ClickListener?) {
        clickListener = listener
    }
}