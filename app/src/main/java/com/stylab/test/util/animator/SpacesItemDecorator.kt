package com.stylab.test.util.animator

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.annotation.DimenRes


class SpacesItemDecoration(private val mItemOffset: Int) : RecyclerView.ItemDecoration() {

    constructor(context: Context, @DimenRes itemOffsetId: Int) : this(
        context.getResources().getDimensionPixelSize(
            itemOffsetId
        )
    )

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset)
    }
}