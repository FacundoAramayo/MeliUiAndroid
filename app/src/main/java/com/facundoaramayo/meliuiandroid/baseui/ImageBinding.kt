package com.facundoaramayo.meliuiandroid.baseui

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.setImageUrl(url: String?) {
    if (url == null) {
        this.setImageDrawable(null)
    } else {
        Picasso.get().load(url).into(this) // replace with your fav image loading lib
    }
}