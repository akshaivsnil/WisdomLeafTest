package com.akshai.tutorials.wisdomleaftest.ui.bindingAdaptor

import android.view.RoundedCorner
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation


/**
 * Created by ATM on 20/October/2022
 */

@BindingAdapter("bindImage")
fun bindImageView( view : ImageView,url : String,){
    view.load(url){
        crossfade(true)
    }
}

@BindingAdapter("bindImageCurved")
fun bindImageCurved( view : ImageView,url : String,){
    view.load(url){
        crossfade(true)
        transformations(RoundedCornersTransformation(20F))
    }
}