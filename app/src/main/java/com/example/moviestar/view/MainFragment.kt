package com.example.moviestar.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import com.example.moviestar.R
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
    private val adapter_rus = MainAdapter()
    private val adapter_world = MainAdapter()

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
        with(binding) {
            mainRecyclerViewForRusMovie.adapter = adapter_rus
            mainRecyclerViewForWorldMovie.adapter = adapter_world
        }

        adapter_rus.listener = MainAdapter.OnItemClick { movie ->
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

        adapter_world.listener = MainAdapter.OnItemClick { movie ->
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

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getData().observe(viewLifecycleOwner, {state
            -> render(state)
        })


        viewModel.getMovieFromLocalStorageRus()
    }

    private fun render(state: AppState) {
        when (state) {
            is AppState.Success<*> -> {
                val movie: List<Movie> = state.data as List<Movie>
                val isRussian = state.isRussian
                if (isRussian) {
                    adapter_rus.setMovie(movie)
                    if (binding.mainRecyclerViewForWorldMovie.isEmpty()) {
                        viewModel.getMovieFromLocalStorageWorld()

                    }
                } else {
                    adapter_world.setMovie(movie)
                }
                binding.loadingContainer.visibility = View.GONE
                binding.recyclerViewContainer.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.recyclerViewContainer.visibility = View.GONE
                binding.loadingContainer.visibility = View.VISIBLE
                binding.mainFragment.showSnackBar(state.error.message.toString(), 
                    Snackbar.LENGTH_INDEFINITE,
                    R.string.one_more_time) { viewModel.getMovieFromLocalStorageRus() }
            }
            is AppState.Loading -> {
                binding.recyclerViewContainer.visibility = View.GONE
                binding.loadingContainer.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}