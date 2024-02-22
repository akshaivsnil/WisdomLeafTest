package com.akshai.tutorials.wisdomleaftest.repository

sealed class DataHandler<out T> {
    data class SUCCESS<out T>(val data: T) : DataHandler<T>()
    data class ERROR(val message: String) : DataHandler<Nothing>()
    object LOADING : DataHandler<Nothing>()


}