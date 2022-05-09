package com.ex.shoppingex.utility

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    if (url.isNullOrEmpty()) {
        return
    }

    Glide.with(imageView.context)
        .load(url)
        .fitCenter()
        .into(imageView)
}