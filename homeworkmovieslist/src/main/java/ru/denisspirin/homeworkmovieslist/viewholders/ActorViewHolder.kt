package ru.denisspirin.homeworkmovieslist.viewholders

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.denisspirin.homework3uicomponents2.R
import ru.denisspirin.homeworkmovieslist.data.models.Actor
import ru.denisspirin.homeworkmovieslist.data.models.Movie

class ActorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // save current context
    private val context: Context = view.context

    // find components
    private val ivActor : ImageView = view.findViewById(R.id.ivActor)
    private val tvActor : TextView = view.findViewById(R.id.tvActor)

    fun onBind(actor: Actor) {
        ivActor.setImageResource(context.resources.getIdentifier(actor.image, "drawable", context.packageName))
        tvActor.text = actor.name
    }
}