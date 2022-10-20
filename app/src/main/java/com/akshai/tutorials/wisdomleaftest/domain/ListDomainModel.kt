package com.akshai.tutorials.wisdomleaftest.domain

import android.graphics.Color
import java.util.*


/**
 * Created by ATM on 20/October/2022
 */

data class ListDomainModel(
    val author: String? = null,
    val width: Int? = null,
    val downloadUrl: String? = null,
    val id: String? = null,
    val url: String? = null,
    val height: Int? = null,
    var color : Int = Color.argb(100,  Random().nextInt(256),  Random().nextInt(256),  Random().nextInt(256))
)