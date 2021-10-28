package com.arrayyan.favorite.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.arrayyan.core.domain.model.ContentModel
import com.arrayyan.core.ui.BaseFragment
import com.arrayyan.core.utils.setPopupMenu
import com.arrayyan.dicoding.fragment.MoviesFragment
import com.arrayyan.dicoding.ui.detail.ContentActivity
import com.arrayyan.dicoding.ui.detail.DetailContentViewModel
import com.arrayyan.favorite.R
import com.arrayyan.favorite.adapter.ContentDatabaseAdapter
import com.arrayyan.favorite.databinding.FragmentFavoriteTvShowsBinding
import com.arrayyan.favorite.viewmodel.TvShowsFavoriteViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowsFavoriteFragment : BaseFragment<FragmentFavoriteTvShowsBinding>() {

    private val tvShowsFavoriteViewModel: TvShowsFavoriteViewModel by viewModel()
    private val detailContentViewModel: DetailContentViewModel by viewModel()
    private lateinit var contentDatabaseAdapter: ContentDatabaseAdapter
    private var tvShows: ArrayList<ContentModel> = ArrayList()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavoriteTvShowsBinding
        get() = FragmentFavoriteTvShowsBinding::inflate

    override fun setup() {
        getData()
        initOnClick()
        setupRecyclerView()
    }

    private fun initOnClick() {
        contentDatabaseAdapter.onItemClick = { selectedData, action ->
            if (action == ContentDatabaseAdapter.DETAIL_CONTENT) {
                val intent = Intent(activity, ContentActivity::class.java)
                intent.putExtra(ContentActivity.EXTRA_DATA, selectedData)
                intent.putExtra(ContentActivity.LOAD_FROM, MoviesFragment.MOVIE)
                startActivity(intent)
            } else {
                val mAlertDialog = AlertDialog.Builder(requireActivity())
                mAlertDialog.setIcon(R.drawable.ic_delete)
                mAlertDialog.setTitle(resources.getString(R.string.delete_title))
                mAlertDialog.setMessage(resources.getString(R.string.confirmation_delete))
                mAlertDialog.setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                    val statusFavorite = !selectedData.isFavorite
                    detailContentViewModel.setFavoriteTvShow(selectedData, statusFavorite)
                }
                mAlertDialog.setNegativeButton(resources.getString(R.string.no)) { _, _ ->
                }
                mAlertDialog.show()
            }
        }

        val menu = R.menu.menu_sort
        binding.ibSortTv.setOnClickListener {
            if (tvShows.isNotEmpty()) {
                setPopupMenu(requireActivity(), binding.ibSortTv, menu) { its ->
                    when (its.itemId) {
                        R.id.menu_asc -> {
                            val tvShowsSort: List<ContentModel> = tvShows.sortedBy { it.title }
                            contentDatabaseAdapter.setData(tvShowsSort)
                            contentDatabaseAdapter.notifyItemRangeChanged(0, tvShowsSort.size)
                        }
                        R.id.menu_desc -> {
                            val tvShowsSort: List<ContentModel> =
                                tvShows.sortedByDescending { it.title }
                            contentDatabaseAdapter.setData(tvShowsSort)
                            contentDatabaseAdapter.notifyItemRangeChanged(0, tvShowsSort.size)
                        }
                    }
                }
            }
        }
    }

    private fun getData() {
        contentDatabaseAdapter = ContentDatabaseAdapter()
        tvShowsFavoriteViewModel.favoriteTvShow.observe(viewLifecycleOwner, { tvShow ->
            if (!tvShow.isNullOrEmpty()) {
                contentDatabaseAdapter.setData(tvShow)
                tvShows.addAll(tvShow)
                binding.progressBar.visibility = View.GONE
                binding.gTvFavoriteBlank.visibility = View.GONE
            } else {
                contentDatabaseAdapter.setData(tvShow)
                tvShows.clear()
                binding.progressBar.visibility = View.GONE
                binding.gTvFavoriteBlank.visibility = View.VISIBLE
            }
        })
    }

    private fun setupRecyclerView() {
        with(binding.rvTvShowListFavorite) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = contentDatabaseAdapter
        }
    }
}