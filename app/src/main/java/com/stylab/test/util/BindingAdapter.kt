package com.stylab.test.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.signature.ObjectKey


@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        OkHttpGlide.with(view.context)
            .load(imageUrl)
            .transition(withCrossFade())
            .signature(ObjectKey(System.currentTimeMillis()))
            .into(view)
    }
}



