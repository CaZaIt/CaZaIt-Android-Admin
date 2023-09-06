package org.cazait.presentation.util

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import org.cazait.presentation.R

@BindingAdapter("loadImage")
fun ImageView.loadImage(uri: Uri?) {
    load(uri) {
        crossfade(true)
        placeholder(R.drawable.cazait_frame)
    }
}