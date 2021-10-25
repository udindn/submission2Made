package com.arrayyan.favorite

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.arrayyan.core.ui.BaseActivity
import com.arrayyan.core.ui.BaseViewPager2Adapter
import com.arrayyan.dicoding.R
import com.arrayyan.favorite.databinding.ActivityFavoriteBinding
import com.arrayyan.favorite.di.favoriteModule
import com.arrayyan.favorite.fragment.MoviesFavoriteFragment
import com.arrayyan.favorite.fragment.TvShowsFavoriteFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.core.context.loadKoinModules

class FavoriteActivity : BaseActivity<ActivityFavoriteBinding>(), TabLayout.OnTabSelectedListener {

    override val bindingInflater: (LayoutInflater) -> ActivityFavoriteBinding
        get() = ActivityFavoriteBinding::inflate

    override fun setup(savedInstanceState: Bundle?) {
        loadKoinModules(favoriteModule)

        setupViewPager()
    }

    private fun setupViewPager() {
        val fragments = ArrayList<Fragment>()
        val moviesFrag = MoviesFavoriteFragment()
        fragments.add(moviesFrag)
        val tvShowFrag = TvShowsFavoriteFragment()
        fragments.add(tvShowFrag)

        val titlesOfTab = arrayOf(
            getString(R.string.movie),
            getString(R.string.tv)
        )

        binding.vpContent.adapter =
            BaseViewPager2Adapter(this, fragments)
        binding.vpContent.offscreenPageLimit = fragments.size
        binding.tlTitleTabs.addOnTabSelectedListener(this)

        TabLayoutMediator(
            binding.tlTitleTabs,
            binding.vpContent) {
                tab, position -> tab.text = titlesOfTab[position]
        }.attach()

        setOnSelectedView(binding.tlTitleTabs)
    }

    private fun setOnSelectedTab(selected: Boolean, tab: TabLayout.Tab) {
        if (selected) {
            val tabs = (binding.tlTitleTabs.getChildAt(0) as ViewGroup).getChildAt(tab.position) as LinearLayout
            val tabTextView = tabs.getChildAt(1) as TextView
            tabTextView.setTypeface(tabTextView.typeface, Typeface.BOLD)
        } else {
            val tabs = (binding.tlTitleTabs.getChildAt(0) as ViewGroup).getChildAt(tab.position) as LinearLayout
            val tabTextView = tabs.getChildAt(1) as TextView
            tabTextView.setTypeface(null, Typeface.NORMAL)
        }
    }

    private fun setOnSelectedView(tabLayout: TabLayout) {
        val tab = tabLayout.getTabAt(0)
        if (tab != null) {
            val tabs =
                (tabLayout.getChildAt(0) as ViewGroup).getChildAt(tab.position) as LinearLayout
            val tabTextView = tabs.getChildAt(1) as TextView
            tabTextView.setTypeface(tabTextView.typeface, Typeface.BOLD)
            tab.select()
        }
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        if (tab != null) {
            setOnSelectedTab(false, tab)
        }
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        if (tab != null) {
            setOnSelectedTab(true, tab)
        }
    }
}