package com.app.mediadbapp.extensions

import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import com.app.mediadbapp.baseClasses.BaseFragment
import com.app.mediadbapp.callback.ReloadFragmentCallback

fun Fragment.showMessage(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.getActiveFragment(frameId: Int): Fragment? {
    return childFragmentManager.findFragmentById(frameId)
}

fun Fragment.replaceFragmentIfRequiredAllowingStateLoss(
    fragment: BaseFragment,
    frameId: Int,
    forceReplace: Boolean = false
) {
    val activeFragment = getActiveFragment(frameId)
    if (!forceReplace && fragment.isTypeSame(activeFragment)) {
        (activeFragment as? ReloadFragmentCallback?)?.reloadFragment()
        return
    }

    replaceFragmentAllowingStateLoss(fragment = fragment, frameId = frameId)
}


fun Fragment.replaceFragmentAllowingStateLoss(fragment: Fragment, frameId: Int) {
    childFragmentManager.transactAllowingStateLoss {
        replace(frameId, fragment)
    }
}

fun Fragment.getDimen(@DimenRes id: Int): Int {
    return context?.getDimen(id) ?: 0
}

fun Fragment.getCompatColor(@ColorRes id: Int): Int {
    return context?.getCompatColor(id) ?: 0
}

fun Fragment.getCompatDrawable(@DrawableRes id: Int): Drawable? {
    return context?.getCompatDrawable(id)
}


