package com.arrayyan.dicoding.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.arrayyan.core.ui.BaseActivity
import com.arrayyan.core.ui.BaseViewPager2Adapter
import com.arrayyan.core.utils.toast
import com.arrayyan.dicoding.R
import com.arrayyan.dicoding.databinding.ActivityMainBinding
import com.arrayyan.dicoding.fragment.MoviesFragment
import com.arrayyan.dicoding.fragment.TvShowsFragment

class MainActivity : BaseActivity<ActivityMainBinding>(), ViewPager.OnPageChangeListener {

    private var activated: BooleanArray = booleanArrayOf(false, false, false)
    private lateinit var onPageChangeCallback: ViewPager2.OnPageChangeCallback

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setup(savedInstanceState: Bundle?) {
        setupToolbar(R.id.toolbar, false, getString(R.string.label_main_menu), R.color.colorRed, 4f)
        initView()
    }

    private fun setActive(pos: Int) {
        activated[pos] = true
        when (pos) {
            0 -> {
                binding.bottomNavView.selectedItemId = R.id.navMovie
            }
            1 -> {
                binding.bottomNavView.selectedItemId = R.id.navTvShow
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_settings) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        } else if (item.itemId == R.id.iv_favorite){
            val uri = Uri.parse("contentfavorite://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
        return super.onOptionsItemSelected(item)
    }

    private var exit = false
    override fun onBackPressed() {
        if (exit) super.onBackPressed() else {
            toast(getString(R.string.click_button_one_more_to_exit))
            exit = true
            Handler(Looper.getMainLooper()).postDelayed({ exit = false }, 3000)
        }
    }

    private fun initView() {
        val fragments = ArrayList<Fragment>()
        val moviesFrag = MoviesFragment()
        fragments.add(moviesFrag)
        val tvShowFrag = TvShowsFragment()
        fragments.add(tvShowFrag)
        arrayOf(resources.getString(R.string.movie), resources.getString(R.string.tv))

        binding.pager.adapter = BaseViewPager2Adapter(this, fragments)
        binding.pager.offscreenPageLimit = fragments.size
        binding.pager.isUserInputEnabled = false
        onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setActive(position)
                super.onPageSelected(position)
            }
        }
        binding.pager.registerOnPageChangeCallback(onPageChangeCallback)


        binding.bottomNavView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navMovie -> {
                    binding.pager.currentItem = 0
                }

                R.id.navTvShow -> {
                    binding.pager.currentItem = 1
                }
            }
            true
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        TODO("Not yet implemented")
    }

    override fun onPageSelected(position: Int) {
        setActive(position)
    }

    override fun onPageScrollStateChanged(state: Int) {
        TODO("Not yet implemented")
    }
}