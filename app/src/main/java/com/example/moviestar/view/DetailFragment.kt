package com.example.moviestar.view

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.moviestar.R
import com.example.moviestar.databinding.DetailFragmentBinding
import com.example.moviestar.model.*
import com.example.moviestar.viewmodel.DetailViewModel
import java.util.*

class DetailFragment : Fragment() {

    private var movie: Movie? = null
    private var isFavourite: Boolean? = false

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this).get(DetailViewModel::class.java)
    }

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
            binding.releaseDate.text = movie.releaseYear
            binding.voteAverage.text = movie.voteAverage.toString()
            binding.overview.text = movie.overview
            binding.tagline.text = movie.tagline
            binding.movieImage.load("https://www.themoviedb.org/t/p/original${movie.movieImage}")

            this.movie = movie
            viewModel.saveHistory(movie)
            viewModel.updateTimestamp(Date().time, movie.id)
            binding.myNote.text = (viewModel.getOneMovieHistory(movie.id).note ?: 0) as CharSequence?
            if (viewModel.getOneMovieHistory(movie.id).isFavourite) binding.favouriteFab.setImageResource(R.drawable.favourite_icon)
        } ?: Toast.makeText(context, R.string.fail, Toast.LENGTH_LONG).show()
    }
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

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

        binding.noteEditText.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                if (p2?.action == KeyEvent.ACTION_DOWN && p1 == KeyEvent.KEYCODE_ENTER) {
                    binding.myNote.text = binding.noteEditText.editableText
                    viewModel.updateNote(movie?.id ?: 0, binding.noteEditText.editableText.toString())
                    with(binding.noteEditText) {
                        clearFocus()
                        isCursorVisible = false
                        text.clear()
                    }
                    return true
                }
                return false
            }

        })

        binding.favouriteFab.setOnClickListener{
            isFavourite = !viewModel.getOneMovieHistory(movie?.id ?: 0).isFavourite
            viewModel.updateFavourite(movie?.id ?: 0, isFavourite ?: false)
            if(isFavourite == true) {
                binding.favouriteFab.setImageResource(R.drawable.favourite_icon)
            } else {
                binding.favouriteFab.setImageResource(R.drawable.not_favourite_icon)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        RepositoryImpl.removeLoadListener(listener)
        _binding = null
    }
}