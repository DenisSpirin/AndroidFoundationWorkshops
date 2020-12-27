package ru.denisspirin.homeworkmovieslist.ui.viewholders

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.denisspirin.homework3uicomponents2.R
import ru.denisspirin.homeworkmovieslist.data.models.Actor

class ActorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // save current context
    private val context: Context = view.context

    // find components
    private val ivActor : ImageView = view.findViewById(R.id.ivActor)
    private val tvActor : TextView = view.findViewById(R.id.tvActor)

    fun onBind(actor: Actor) {
        tvActor.text = actor.name

        Glide.with(context)
                .load(actor.picture)
                .into(ivActor)
    }
}