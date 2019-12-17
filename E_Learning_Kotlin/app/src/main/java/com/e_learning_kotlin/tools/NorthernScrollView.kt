package com.e_learning_kotlin.tools

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView
import com.e_learning_kotlin.ui.notifications.NotificationsFragment


class NorthernScrollView : ScrollView {
    private var scrollViewListener: NorthernScrollViewListener? = null
    fun setScrollViewListener(scrollViewListener: NotificationsFragment) {
        this.scrollViewListener = scrollViewListener
    }

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        if (scrollViewListener != null) {
            scrollViewListener!!.onScrollChanged(this, l, t, oldl, oldt)
        }
    }

    interface NorthernScrollViewListener {
        fun onScrollChanged(
            scrollView: NorthernScrollView?,
            x: Int,
            y: Int,
            oldx: Int,
            oldy: Int
        )
    }
}