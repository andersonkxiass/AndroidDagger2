package com.example.tools.features.movies.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tools.R
import com.example.tools.models.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    val movies : MutableList<Movie> = mutableListOf()

    fun setData( movies : MutableList<Movie>){
        this.movies.clear()
        this.movies.addAll( movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = this.movies[position]
        holder.setData(movie)

        holder.itemView.setOnClickListener {
            it.findNavController().navigate(ListFragmentDirections.navToUpdate(movie))
        }

        holder.itemView.setOnLongClickListener{
            it.findNavController().navigate(ListFragmentDirections.navToDelete(movie.movieId!!))
            true
        }
    }

    inner class MovieViewHolder(private  val view : View) : RecyclerView.ViewHolder(view){

        fun setData(movie: Movie) {
            view.movie_title.text = movie.title
        }
    }
}