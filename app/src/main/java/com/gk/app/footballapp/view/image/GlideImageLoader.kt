package com.gk.app.footballapp.view.image

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * ImageLoader implementation using Glide library.
 */
class GlideImageLoader : ImageLoader {
    override fun loadImage(imageView: ImageView, url: String) {
        Glide
            .with(imageView)
            .load(url)
            .centerCrop()
//            .placeholder(android.R.drawable.?)
            .into(imageView);
    }
}