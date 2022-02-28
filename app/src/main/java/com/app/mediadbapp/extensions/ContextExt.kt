package com.app.mediadbapp.extensions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat


fun Context.getCompatColor(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this, id)
}

fun Context.getCompatDrawable(@DrawableRes id: Int): Drawable? {
    return ContextCompat.getDrawable(this, id)
}

fun Context.getDimen(@DimenRes id: Int): Int {
    return resources.getDimensionPixelSize(id)
}

fun Context.showMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context?.getString(@StringRes id: Int): String {
    return this?.getString(id) ?: ""
}

fun Context?.getWebOpeningClickListener(url: String): View.OnClickListener {
    return View.OnClickListener {
        this?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}

fun Context?.checkForPermission(permission: String): Boolean {
    this ?: return false
    val result: Int = ContextCompat.checkSelfPermission(this, permission)

    return result == PackageManager.PERMISSION_GRANTED
}
