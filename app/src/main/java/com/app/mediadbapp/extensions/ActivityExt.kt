package com.app.mediadbapp.extensions

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import android.view.WindowManager
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.mediadbapp.R
import com.app.mediadbapp.baseClasses.BaseFragment
import com.app.mediadbapp.callback.ReloadFragmentCallback
import java.io.Serializable

fun Activity.startActivity(cls: Class<*>) {
    startActivity(Intent(this, cls))
}

fun Activity.startActivity(cls: Class<*>, param: Map<String, Any?>) {
    startActivity(Intent(this, cls).apply {
        param.forEach {
            it.value.let { value ->
                if (value is Parcelable)
                    putExtra(it.key, value)
                if (value is Serializable)
                    putExtra(it.key, value)
            }
        }
    })
}

fun Activity.launchForResult(
    resultLauncher: ActivityResultLauncher<Intent>,
    cls: Class<*>,
    param: Map<String, Any?>
) {
    resultLauncher.launch(Intent(this, cls).apply {
        param.forEach {
            it.value.let { value ->
                if (value is Parcelable)
                    putExtra(it.key, value)
                if (value is Serializable)
                    putExtra(it.key, value)
            }
        }
    })
}

fun Activity.launchForResult(resultLauncher: ActivityResultLauncher<Intent>, cls: Class<*>) {
    resultLauncher.launch(Intent(this, cls))
}

fun AppCompatActivity.replaceFragmentInActivity(
    fragment: Fragment,
    frameId: Int,
    animate: Boolean = false
) {
    supportFragmentManager.transact {
//        if (animate)
//            setCustomAnimations(R.anim.fragment_open_enter, R.anim.fragment_open_exit)
        replace(frameId, fragment)
    }
}

fun AppCompatActivity.replaceFragmentIfRequiredAllowingStateLoss(
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

fun AppCompatActivity.replaceFragmentAllowingStateLoss(fragment: Fragment, frameId: Int) {
    supportFragmentManager.transactAllowingStateLoss {
        replace(frameId, fragment)
    }
}


fun Intent.clearActivityStack() {
    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
}

fun AppCompatActivity.getActiveFragment(frameId: Int): Fragment? {
    return supportFragmentManager.findFragmentById(frameId)
}

fun AppCompatActivity.replaceFragment(
    frameId: Int = R.id.content,
    fragment: Fragment,
    backStackEntry: String? = null,
    addToBackStack: Boolean = !backStackEntry.isNullOrBlank()
) {
    val canAdd = addToBackStack && (supportFragmentManager.findFragmentById(frameId) != null)
    supportFragmentManager.transact {
        if (canAdd) {
            setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_left,
                R.anim.slide_in_right,
                R.anim.slide_out_right
            )
        }
        replace(frameId, fragment)
        if (canAdd)
            addToBackStack(backStackEntry)
    }
}

fun Activity.updateScreenTouchAbility(isProgressVisible: Boolean) {
    if (isProgressVisible) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    } else {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}