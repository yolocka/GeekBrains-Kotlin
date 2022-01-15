package com.example.moviestar.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.moviestar.R
import com.example.moviestar.databinding.FragmentFavouriteMovieBinding
import com.example.moviestar.databinding.MainFragmentBinding
import com.example.moviestar.model.Movie
import com.example.moviestar.viewmodel.DetailViewModel
import com.example.moviestar.viewmodel.FavouriteViewModel

class FavouriteMovieFragment : Fragment() {

    private val viewModel: FavouriteViewModel by lazy {
        ViewModelProvider(this).get(FavouriteViewModel::class.java)
    }
    private var _binding: FragmentFavouriteMovieBinding? = null
    private val binding get() = _binding!!
    private val adapter = MainAdapter()

    companion object {
        fun newInstance() = FavouriteMovieFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavouriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainRecyclerViewForFavouriteMovies.adapter = adapter
        adapter.listener = MainAdapter.OnItemClick { movie ->
            val bundle = Bundle().apply {
                putParcelable("MOVIE_EXTRA", movie)
            }
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .replace(R.id.main_container, DetailFragment.newInstance(bundle))
                    .addToBackStack("main")
                    .commit()
            }
        }
        adapter.setMovie(getFavouriteMovies(viewModel.getAllHistory()))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun getFavouriteMovies(movies: List<Movie>) : List<Movie> {
        var favMovies: ArrayList<Movie> = arrayListOf()
        movies.forEach {
            if (it.isFavourite) {
                favMovies.add(it)
            }
        }
        return favMovies
    }
}