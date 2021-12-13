package com.example.moviestar.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviestar.databinding.MainFragmentBinding
import com.example.moviestar.model.Movie
import com.example.moviestar.viewmodel.AppState
import com.example.moviestar.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getData().observe(viewLifecycleOwner, {state
            -> render(state)
        })

        viewModel.getMovie()
    }

    private fun render(state: AppState) {
        when (state) {
            is AppState.Success -> {
                binding.loadingContainer.visibility = View.GONE
                val movie = state.movie as Movie
                binding.movieItem.originalTitle.text = movie.movieName
                binding.movieItem.releaseYear.text = movie.releaseYear.toString()
                binding.movieItem.voteAverage.text = movie.rating.toString()
            }
            is AppState.Error -> {
                binding.loadingContainer.visibility = View.VISIBLE
                Snackbar.make(binding.root, state.error.message.toString(), Snackbar.LENGTH_INDEFINITE)
                    .setAction("Еще раз") {
                        viewModel.getMovie()
                    }.show()
            }
            is  AppState.Loading -> {
                binding.loadingContainer.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}