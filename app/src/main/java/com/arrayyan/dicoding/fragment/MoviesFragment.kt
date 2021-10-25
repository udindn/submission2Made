package com.arrayyan.dicoding.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arrayyan.core.data.Resource
import com.arrayyan.core.ui.BaseFragment
import com.arrayyan.dicoding.databinding.FragmentMoviesBinding
import com.arrayyan.dicoding.fragment.adapter.ContentAdapter
import com.arrayyan.dicoding.fragment.viewmodel.MoviesViewModel
import com.arrayyan.dicoding.ui.detail.ContentActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : BaseFragment<FragmentMoviesBinding>() {

    companion object{
        const val MOVIE = 1
    }

    private val moviesViewModel: MoviesViewModel by viewModel()
    private lateinit var contentAdapter: ContentAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMoviesBinding
        get() = FragmentMoviesBinding::inflate

    override fun setup() {
        getResponse()
        setupRecyclerView()
        initOnClick()
    }

    private fun getResponse() {
        contentAdapter = ContentAdapter()
        moviesViewModel.movie.observe(viewLifecycleOwner, { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        contentAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        with(binding.rvMovieList) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = contentAdapter
        }
    }

    private fun initOnClick() {
        contentAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, ContentActivity::class.java)
            intent.putExtra(ContentActivity.EXTRA_DATA, selectedData)
            intent.putExtra(ContentActivity.LOAD_FROM, MOVIE)
            startActivity(intent)
        }
    }
}