package com.ayokunle.sanwoolu.utils.image

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(url: String, imageView: ImageView)
}