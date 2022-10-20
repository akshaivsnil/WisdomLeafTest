package com.akshai.tutorials.wisdomleaftest.domain

import com.google.gson.annotations.SerializedName


/**
 * Created by ATM on 20/October/2022
 */

data class ListDomainModel(
    val author: String? = null,
    val width: Int? = null,
    val downloadUrl: String? = null,
    val id: String? = null,
    val url: String? = null,
    val height: Int? = null
)
