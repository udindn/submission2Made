package com.arrayyan.core.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun loadImage(context: Context, url: String?, imageView: ImageView, placeholder: Int, progressBar: View) {
    if (url == null || url.isEmpty()) {
        progressBar.visibility = View.GONE
    } else {
        progressBar.visibility = View.VISIBLE
        Glide.with(context)
                .load(url)
                .placeholder(placeholder)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }
                })
                .into(imageView)
    }
}

fun loadImage(context: Context, url: String?, imageView: ImageView) {
    Glide.with(context)
        .load(url)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
            ): Boolean {
                return false
            }
        })
        .into(imageView)
}

fun parseDateToYear(times: String): String? {
    val inputPattern = "yyyy-MM-dd"
    val inputFormat = SimpleDateFormat(inputPattern, Locale("en", "EN"))
    var time: String? = null
    try {
        val cal = inputFormat.parse(times)
        val format = "yyyy"
        val sdf = SimpleDateFormat(format, Locale("en", "EN"))
        time = sdf.format(cal!!)
        return time
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return time
}

fun setPopupMenu(
        context: Context,
        view: View,
        menu: Int,
        item: (item: MenuItem) -> Unit
) {
    val popup = PopupMenu(context, view)
    popup.menuInflater.inflate(menu, popup.menu)
    popup.setOnMenuItemClickListener { items ->
        item(items)
        true
    }
    popup.show()
}