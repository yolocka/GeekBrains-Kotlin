package com.example.moviestar.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.moviestar.databinding.DetailFragmentBinding
import com.example.moviestar.model.Movie
import com.example.moviestar.model.MovieDTO
import com.example.moviestar.model.MovieLoader
import com.example.moviestar.viewmodel.AppState
import com.example.moviestar.viewmodel.DetailViewModel
import com.google.android.material.snackbar.Snackbar

class DetailFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle?) : DetailFragment {
            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Movie>("MOVIE_EXTRA")?.let { movie ->
            MovieLoader.load(movie, object : MovieLoader.OnMovieLoadedListener {
                override fun onLoaded(movieDTO: MovieDTO) {
                    movieDTO?.let {
                        binding.originalTitle.text = it.title
                        binding.releaseDate.text = it.releaseDate.toString().takeLast(4)
                        binding.voteAverage.text = it.voteAverage.toString()
                        binding.overview.text = it.overview
                        binding.tagline.text = it.tagline
                    }
                }

                override fun onFailed(throwable: Throwable) {
                    Toast.makeText(requireContext(), throwable.message, Toast.LENGTH_LONG).show()
                }

            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}