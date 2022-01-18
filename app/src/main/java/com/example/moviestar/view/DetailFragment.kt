package com.example.moviestar.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.moviestar.R
import com.example.moviestar.databinding.DetailFragmentBinding
import com.example.moviestar.model.*
import com.example.moviestar.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle?) : DetailFragment {
            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val listener = Repository.OnLoadListener {
        RepositoryImpl.getMovieFromServer()?.let { movie ->
            binding.originalTitle.text = movie.title
            binding.releaseDate.text = movie.releaseYear.toString()
            binding.voteAverage.text = movie.voteAverage.toString()
            binding.overview.text = movie.overview
            binding.tagline.text = movie.tagline
        } ?: Toast.makeText(context, R.string.fail, Toast.LENGTH_LONG).show()
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

        RepositoryImpl.addLoaderListener(listener)

        arguments?.getParcelable<Movie>("MOVIE_EXTRA")?.let { movie ->

            requireActivity().startService(Intent(requireContext(), MainIntentService::class.java).apply {
                putExtra("MOVIE_EXTRA", movie)
            })

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        RepositoryImpl.removeLoadListener(listener)
        _binding = null
    }
}