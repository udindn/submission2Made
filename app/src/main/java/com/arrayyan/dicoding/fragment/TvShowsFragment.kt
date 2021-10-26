package com.arrayyan.dicoding.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arrayyan.core.data.Resource
import com.arrayyan.core.ui.BaseFragment
import com.arrayyan.dicoding.databinding.FragmentTvShowsBinding
import com.arrayyan.dicoding.fragment.adapter.ContentAdapter
import com.arrayyan.dicoding.fragment.viewmodel.TvShowsViewModel
import com.arrayyan.dicoding.ui.detail.ContentActivity
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowsFragment : BaseFragment<FragmentTvShowsBinding>() {

    companion object{
        const val TV_SHOW = 2
    }

    private val tvShowsViewModel: TvShowsViewModel by viewModel()
    private lateinit var contentAdapter: ContentAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTvShowsBinding
        get() = FragmentTvShowsBinding::inflate

    override fun setup() {
        contentAdapter = ContentAdapter()
        getResponse()
        setupRecyclerView()
        initOnClick()
    }

    private fun getResponse() {
        contentAdapter = ContentAdapter()
        tvShowsViewModel.tvShow.observe(viewLifecycleOwner, { tvShow ->
            if (tvShow != null) {
                when (tvShow) {
                    is Resource.Loading -> binding.pbLoadListTv.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.pbLoadListTv.visibility = View.GONE
                        contentAdapter.setData(tvShow.data)
                    }
                    is Resource.Error -> {
                        binding.pbLoadListTv.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        with(binding.rvTvShowList) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = contentAdapter
        }
    }

    private fun initOnClick() {
        contentAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, ContentActivity::class.java)
            intent.putExtra(ContentActivity.EXTRA_DATA, selectedData)
            intent.putExtra(ContentActivity.LOAD_FROM, TV_SHOW)
            startActivity(intent)
        }
    }
}