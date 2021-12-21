package com.example.moviestar.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviestar.databinding.DetailFragmentBinding
import com.example.moviestar.model.Movie
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

        val movie = arguments?.getParcelable<Movie>("MOVIE_EXTRA")

        movie?.let {
            binding.originalTitle.text = movie.title
            binding.releaseYear.text = movie.releaseYear.toString()
            binding.voteAverage.text = movie.voteAverage.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}