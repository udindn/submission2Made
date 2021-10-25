package com.arrayyan.core.utils

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun log(tag: String, message: String) {
    Log.d(tag, message)
}

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

fun getImage(context: Context, imageName: String?): Int {
    return context.resources.getIdentifier(imageName, "drawable", context.packageName)
}

fun getApplicationName(context: Context): String {
    val applicationInfo = context.applicationInfo
    val stringId = applicationInfo.labelRes
    return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else context.getString(
            stringId
    )
}

fun hasPermissions(context: Context?, permissions: Array<String>): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null) {
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                            context,
                            permission
                    ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
    }
    return true
}

fun getAssetsJSON(context: Context, dataName: String): String? {
    var json: String? = null
    try {
        val inputStream = context.assets?.open(dataName)
        val size = inputStream?.available()
        val buffer = ByteArray(size!!)
        inputStream.read(buffer)
        inputStream.close()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            json = String(buffer, StandardCharsets.UTF_8)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return json
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

fun parseDateToDate(times: String): String? {
    val inputPattern = "yyyy-MM-dd"
    val inputFormat = SimpleDateFormat(inputPattern, Locale("en", "EN"))
    var time: String? = null
    try {
        val cal = inputFormat.parse(times)
        val format = "dd MMMM yyyy"
        val sdf = SimpleDateFormat(format, Locale("en", "EN"))
        time = sdf.format(cal!!)
        return time
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return time
}

fun minuteToHour(t: Int): String {
    val hours = t / 60
    val minutes = t % 60

    return String.format("%d h %02d m", hours, minutes)
}

fun getBitmapFromURL(src: String?): Bitmap? {
    return try {
        val url = URL(src)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()
        val input: InputStream = connection.inputStream
        BitmapFactory.decodeStream(input)
    } catch (e: IOException) {
        // Log exception
        null
    }
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