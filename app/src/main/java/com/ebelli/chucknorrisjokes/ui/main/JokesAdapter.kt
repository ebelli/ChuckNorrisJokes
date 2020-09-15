package com.ebelli.chucknorrisjokes.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ebelli.chucknorrisjokes.R
import com.ebelli.chucknorrisjokes.data.models.Joke
import kotlinx.android.synthetic.main.item_joke.view.*


class JokesAdapter: RecyclerView.Adapter<JokesAdapter.JokesViewHolder>() {

    private lateinit var jokes: List<Joke>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        val jokesView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_joke, parent, false) as ConstraintLayout

        return JokesViewHolder(jokesView)
    }

    override fun getItemCount() = jokes.size

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        holder.bind(jokes[position])
    }

    fun setData(jokes: List<Joke>) {
        this.jokes = jokes
    }

    class JokesViewHolder(private val jokeView: View): RecyclerView.ViewHolder(jokeView) {
        fun bind (joke: Joke) = with(jokeView) {
            jokes_text_view.text = joke.joke
        }

    }
}