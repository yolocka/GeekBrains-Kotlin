package com.example.moviestar.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestar.R
import com.example.moviestar.model.Movie
import androidx.recyclerview.widget.DiffUtil


class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var movie: List<Movie> = listOf()
    var listener: OnItemClick? = null

    fun setMovie(data: List<Movie>) {
        val mainDiffUtilCallback = MainDiffUtilCallback(movie, data)
        val productDiffResult = DiffUtil.calculateDiff(mainDiffUtilCallback)
        movie = data
        //notifyDataSetChanged()
        productDiffResult.dispatchUpdatesTo(this);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(movie[position])
    }

    override fun getItemCount(): Int = movie.size

    inner class MainViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(movie: Movie) {
            itemView.findViewById<TextView>(R.id.original_title_recycle).text = movie.title
            itemView.findViewById<TextView>(R.id.release_year_recycle).text = movie.releaseYear.toString()
            itemView.findViewById<TextView>(R.id.vote_average_recycle).text = movie.voteAverage.toString()
            itemView.setOnClickListener{
                listener?.onClick(movie)
            }
        }
    }

    fun interface OnItemClick {
        fun onClick(movie: Movie)
    }

}
