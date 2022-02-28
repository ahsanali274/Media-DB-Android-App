package com.app.mediadbapp.utils

import android.content.res.Resources


object MiscUtils {

    val mediaItemPeekSize: Int by lazy { (getScreenHeight() / 100) * 20 }

    fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }
}