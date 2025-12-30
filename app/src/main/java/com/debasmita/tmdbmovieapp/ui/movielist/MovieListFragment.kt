package com.debasmita.tmdbmovieapp.ui.movielist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.debasmita.tmdbmovieapp.R
import com.debasmita.tmdbmovieapp.databinding.FragmentMovieListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/*
class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieListViewModel by viewModels()
    private lateinit var adapter: MoviePagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieListBinding.bind(view)

        adapter = MoviePagingAdapter { movie ->
            val action =
                MovieListFragmentDirections
                    .actionMovieListFragmentToMovieDetailFragment(movie)
            findNavController().navigate(action)
        }

        Log.d("MovieListFragment", "--MovieListFragment created")
        binding.movieRecycler.layoutManager =
            GridLayoutManager(requireContext(), 3)

        binding.movieRecycler.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movies.collectLatest {
                Log.d("MovieListFragment", "--M Paging data received")
                adapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


 */


class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private val viewModel: MovieListViewModel by viewModels()
    private lateinit var adapter: MoviePagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.movieRecycler)
        val searchInput = view.findViewById<EditText>(R.id.searchInput)
        val emptyText = view.findViewById<TextView>(R.id.emptyText)

        adapter = MoviePagingAdapter { movie ->
            val action =
                MovieListFragmentDirections
                    .actionMovieListFragmentToMovieDetailFragment(movie)
            findNavController().navigate(action)
        }

        recycler.layoutManager = GridLayoutManager(requireContext(), 3)
        recycler.adapter = adapter

        // Collect paging data
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movies.collectLatest {
                adapter.submitData(it)
            }
        }

        adapter.addLoadStateListener { state ->
            emptyText.visibility =
                if (state.refresh is LoadState.NotLoading && adapter.itemCount == 0) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
        }


        // Search input
//        searchInput.setOnEditorActionListener { v, _, _ ->
//            viewModel.search(v.text.toString())
//            true
//        }

        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.search(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

    }
}
