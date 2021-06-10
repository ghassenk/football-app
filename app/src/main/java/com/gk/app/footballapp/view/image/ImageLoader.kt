package com.gk.app.footballapp.view.image

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(imageView: ImageView, url: String)
}