package com.arrayyan.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<viewBinding : ViewBinding> : AppCompatActivity() {

    private var _binding: ViewBinding? = null
    private var toolbar: Toolbar? = null
    private var subtitles: String? = null
    abstract val bindingInflater: (LayoutInflater) -> viewBinding

    @Suppress("UNCHECKED_CAST")
    protected val binding: viewBinding
        get() = _binding as viewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        setup(savedInstanceState)
    }

    abstract fun setup(savedInstanceState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun setupToolbar(
        toolbarId: Int,
        isBackButtonEnable: Boolean,
        title: String,
        @ColorRes color: Int,
        elevation: Float
    ) {
        toolbar = findViewById<Toolbar>(toolbarId)?.apply {
            setNavigationOnClickListener { onBackPressed() }
            this.title = title
            if (subtitles != null) {
                this.subtitle = subtitles
            }
            this.elevation = elevation
            setBackgroundColor(ContextCompat.getColor(applicationContext, color))
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(isBackButtonEnable)
        }
    }
}
