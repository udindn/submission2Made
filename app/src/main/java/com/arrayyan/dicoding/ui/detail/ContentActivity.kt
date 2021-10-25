package com.arrayyan.dicoding.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.arrayyan.core.domain.model.ContentModel
import com.arrayyan.core.ui.BaseActivity
import com.arrayyan.core.utils.loadImage
import com.arrayyan.core.utils.parseDateToYear
import com.arrayyan.dicoding.BuildConfig
import com.arrayyan.dicoding.R
import com.arrayyan.dicoding.databinding.ActivityContentBinding
import com.arrayyan.dicoding.fragment.MoviesFragment
import com.arrayyan.dicoding.fragment.TvShowsFragment
import org.koin.android.viewmodel.ext.android.viewModel

class ContentActivity : BaseActivity<ActivityContentBinding>() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val LOAD_FROM = "load_from"
    }

    private var loadFrom = 0
    private var statusFavorite = false
    private var contentModel: ContentModel? = null
    private val detailContentViewModel: DetailContentViewModel by viewModel()

    override val bindingInflater: (LayoutInflater) -> ActivityContentBinding
        get() = ActivityContentBinding::inflate

    override fun setup(savedInstanceState: Bundle?) {
        setupToolbar(
            R.id.toolbar,
            true,
            getString(R.string.label_content_detail),
            R.color.colorRed,
            4f
        )
        showLoading(true)
        initExtras()
    }

    private fun initExtras() {
        if (intent != null && intent.hasExtra(LOAD_FROM)) {
            loadFrom = intent.getIntExtra(LOAD_FROM, 0)
            if (intent != null && intent.hasExtra(EXTRA_DATA)) {
                contentModel = intent.getParcelableExtra(EXTRA_DATA)
                showDetailContent(contentModel)
            }
        }
    }

    private fun showDetailContent(contentModel: ContentModel?) {
        this.contentModel = contentModel
        statusFavorite = contentModel?.isFavorite ?: false
        contentModel?.let {
            binding.group5.visibility = View.VISIBLE
            showLoading(false)
            if (loadFrom == TvShowsFragment.TV_SHOW) {
                binding.tvCreate.text = resources.getString(R.string.production_countries)
                if (it.createdBy.isNullOrEmpty() || it.createdBy == getString(
                        R.string.label_null
                    )
                ) {
                    binding.tvProductionCountries.text = getString(R.string.none)
                } else {
                    binding.tvProductionCountries.text = it.createdBy
                }
                binding.tvTitle.text = it.title
                title = it.title
                if (it.firstAirDate.isNullOrEmpty() || it.firstAirDate == getString(R.string.label_null)) {
                    binding.tvDate.text = getString(R.string.none)
                } else {
                    binding.tvDate.text = parseDateToYear(it.firstAirDate!!)
                }
                supportActionBar?.title = resources.getString(R.string.tv_details)
                binding.tvRuntime.visibility = View.GONE
                binding.tvRuntimeTitle.visibility = View.GONE
                binding.tvScore.text = "${it.popularity ?: 0.0}"
                binding.tvOriginalLanguage.text = it.originalLanguage
                binding.tvOverview.text = it.overview
                binding.tvVoteAvg.text = "${it.voteAverage ?: 0.0}"
                binding.tvVoteCount.text = "${it.voteCount ?: 0.0}"

                loadImage(
                    this,
                    "${BuildConfig.BASE_URL_POSTER}/w185/${it.posterPath}",
                    binding.ivCover
                )

            } else {
                binding.tvCreate.text = resources.getString(R.string.production_countries)
                if (it.productionCountries.isNullOrEmpty() || it.productionCountries == getString(
                        R.string.label_null
                    )
                ) {
                    binding.tvProductionCountries.text = getString(R.string.none)
                } else {
                    binding.tvProductionCountries.text = it.productionCountries
                }
                binding.tvTitle.text = it.title
                title = it.title
                if (it.releaseDate.isNullOrEmpty() || it.releaseDate == getString(R.string.label_null)) {
                    binding.tvDate.text = getString(R.string.none)
                } else {
                    binding.tvDate.text = parseDateToYear(it.releaseDate!!)
                }
                supportActionBar?.title = resources.getString(R.string.movie_details)
                binding.tvRuntime.visibility = View.GONE
                binding.tvRuntimeTitle.visibility = View.GONE
                binding.tvScore.text = "${it.popularity ?: 0}"
                binding.tvOriginalLanguage.text = it.originalLanguage
                binding.tvOverview.text = it.overview
                binding.tvVoteAvg.text = "${it.voteAverage ?: 0.0}"
                binding.tvVoteCount.text = "${it.voteCount ?: 0.0}"

                loadImage(
                    this,
                    "${BuildConfig.BASE_URL_POSTER}/w185/${it.posterPath}",
                    binding.ivCover
                )
            }
            showLoading(false)
        }
    }


    private fun showLoading(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_content_detail, menu)
        if (contentModel?.isFavorite == true) {
            menu?.findItem(R.id.favorite)?.setIcon(R.drawable.ic_favorite_active)
        } else {
            menu?.findItem(R.id.favorite)?.setIcon(R.drawable.ic_favorite)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                onBackPressed()
            R.id.favorite -> {
                statusFavorite = if (statusFavorite) {
                    item.setIcon(R.drawable.ic_favorite)
                    false
                } else {
                    item.setIcon(R.drawable.ic_favorite_active)
                    true
                }
                if (loadFrom == MoviesFragment.MOVIE)
                    detailContentViewModel.setFavoriteMovie(contentModel!!, statusFavorite)
                else
                    detailContentViewModel.setFavoriteTvShow(contentModel!!, statusFavorite)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}