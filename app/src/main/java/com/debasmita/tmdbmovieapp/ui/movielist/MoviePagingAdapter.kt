package com.debasmita.tmdbmovieapp.ui.movielist


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.debasmita.tmdbmovieapp.R
import com.debasmita.tmdbmovieapp.data.model.Movie

class MoviePagingAdapter(
    private val onClick: (Movie) -> Unit
) : PagingDataAdapter<Movie, MoviePagingAdapter.MovieVH>(DIFF) {

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(o: Movie, n: Movie) = o.id == n.id
            override fun areContentsTheSame(o: Movie, n: Movie) = o == n
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieVH(view)
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class MovieVH(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) {
            val poster = itemView.findViewById<ImageView>(R.id.imgPoster)
            val title = itemView.findViewById<TextView>(R.id.txtTitle)
            val year = itemView.findViewById<TextView>(R.id.txtYear)
            val rating = itemView.findViewById<TextView>(R.id.txtRating)

            title.text = movie.title
            year.text = "${movie.releaseDate.take(4)}"
            rating.text = "${movie.rating}"

            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .into(poster)

            itemView.setOnClickListener { onClick(movie) }
        }
    }
}
