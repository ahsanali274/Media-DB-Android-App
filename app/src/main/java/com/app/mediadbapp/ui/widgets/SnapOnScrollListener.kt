package com.app.mediadbapp.ui.widgets

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import kotlin.math.absoluteValue

class SnapOnScrollListener(
    private val snapHelper: SnapHelper,
    private var onSnapPositionChangeListener: OnSnapPositionChangeListener? = null,
    private var behavior: Behavior = Behavior.NOTIFY_ON_SCROLL
) : RecyclerView.OnScrollListener() {

    enum class Behavior {
        NOTIFY_ON_SCROLL,
        NOTIFY_ON_SCROLL_STATE_IDLE
    }

    private var snapPosition = RecyclerView.NO_POSITION

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (behavior == Behavior.NOTIFY_ON_SCROLL) {
            notifySnapPositionChange(recyclerView)
            if (dy.absoluteValue > 0)
                onSnapPositionChangeListener?.onScrollChanged()
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (behavior == Behavior.NOTIFY_ON_SCROLL_STATE_IDLE
            && newState == RecyclerView.SCROLL_STATE_IDLE
        ) {
            notifySnapPositionChange(recyclerView)
        }
    }

    private fun notifySnapPositionChange(recyclerView: RecyclerView) {
        val snapPosition = snapHelper.getSnapPosition(recyclerView)
        val snapPositionChanged = this.snapPosition != snapPosition
        if (snapPositionChanged) {
            onSnapPositionChangeListener?.onSnapPositionChange(snapPosition)
            this.snapPosition = snapPosition
        }
    }

    fun postUpdate() {
        onSnapPositionChangeListener?.onSnapPositionChange(snapPosition)
    }
}

interface OnSnapPositionChangeListener {
    fun onSnapPositionChange(position: Int)
    fun onScrollChanged() = Unit
}

fun SnapHelper.getSnapPosition(recyclerView: RecyclerView?): Int {
    val layoutManager = recyclerView?.layoutManager ?: return RecyclerView.NO_POSITION
    val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
    return layoutManager.getPosition(snapView)
}
