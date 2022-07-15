package com.oliver.couchpotato.helper

import android.widget.ImageView
import coil.load
import java.text.SimpleDateFormat
import java.util.*

object Extensions {
    fun ImageView.loadImage(imageUrl: String) {
        this.load(imageUrl)
    }

    fun Date.getDateFormatted(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return formatter.format(this)
    }
}