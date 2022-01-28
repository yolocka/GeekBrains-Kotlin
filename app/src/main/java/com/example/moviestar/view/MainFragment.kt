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
    private val adapterRus = MainAdapter()
    private val adapterWorld = MainAdapter()
    private val adapterHistory = MainAdapter()

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            mainRecyclerViewForRusMovie.adapter = adapterRus
            mainRecyclerViewForWorldMovie.adapter = adapterWorld
            mainRecyclerViewForHistoryListMovie.adapter = adapterHistory
        }

        setOnClickListToAdapter(adapterRus)
        setOnClickListToAdapter(adapterWorld)
        setOnClickListToAdapter(adapterHistory)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getData().observe(viewLifecycleOwner, {state
            -> render(state)
        })


        viewModel.getMovieFromLocalStorageRus()

        adapterHistory.setMovie(viewModel.getAllHistory())
    }

    private fun render(state: AppState) {
        when (state) {
            is AppState.Success<*> -> {
                val movie: List<Movie> = state.data as List<Movie>
                val isRussian = state.isRussian
                if (isRussian) {
                    adapterRus.setMovie(movie)
                    if (binding.mainRecyclerViewForWorldMovie.isEmpty()) {
                        viewModel.getMovieFromLocalStorageWorld()

                    }
                } else {
                    adapterWorld.setMovie(movie)
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

    private fun setOnClickListToAdapter(adapter: MainAdapter) {
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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}