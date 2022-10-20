package com.akshai.tutorials.wisdomleaftest.ui.bindingAdaptor

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load


/**
 * Created by ATM on 20/October/2022
 */

@BindingAdapter("bindImage")
fun bindImageView( view : ImageView,url : String,){
    view.load(url){
        crossfade(true)

    }
}