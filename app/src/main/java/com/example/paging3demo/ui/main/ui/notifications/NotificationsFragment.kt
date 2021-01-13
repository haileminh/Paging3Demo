package com.example.paging3demo.ui.main.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging3demo.databinding.FragmentNotificationsBinding
import com.example.paging3demo.ui.adapter.MovieListAdapter
import com.example.paging3demo.ui.adapter.MovieLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationsFragment : Fragment() {

    private val viewModel: NotificationsViewModel by viewModel()
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var binding: FragmentNotificationsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(layoutInflater)
        return binding.root
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieListAdapter = MovieListAdapter()

        binding.movieRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieListAdapter
                .withLoadStateFooter(
                    footer = MovieLoadStateAdapter(movieListAdapter::retry)
                )
        }

        lifecycleScope.launch {
            viewModel.movies.distinctUntilChanged().collectLatest {
                movieListAdapter.submitData(it)
            }
        }
    }
}