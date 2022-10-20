package com.akshai.tutorials.wisdomleaftest.utils

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment


/**
 * Created by ATM on 20/October/2022
 */

fun Context.showToast(msg : String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}