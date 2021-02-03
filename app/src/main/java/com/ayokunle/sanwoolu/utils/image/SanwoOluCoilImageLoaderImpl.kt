package com.ayokunle.sanwoolu.utils.image

import android.widget.ImageView
import coil.load
import coil.size.Scale
import com.ayokunle.sanwoolu.R
import javax.inject.Inject

class SanwoOluCoilImageLoaderImpl @Inject constructor() : ImageLoader {
    override fun loadImage(url: String, imageView: ImageView) {
        imageView.load(url) {
            placeholder(R.drawable.ic_baseline_broken_image)
            scale(Scale.FIT)
            crossfade(true)
        }
    }
}