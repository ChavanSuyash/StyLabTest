package com.stylab.test.util.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView

class ListItemImageView : AppCompatImageView {
    private var aspectRatio = 1.77f

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun setAspectRatio(ratio: Float) {
        aspectRatio = ratio
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = View.MeasureSpec.getSize(widthMeasureSpec)
        val height = View.MeasureSpec.makeMeasureSpec((width * aspectRatio).toInt(), View.MeasureSpec.EXACTLY)

        setMeasuredDimension(width, height)
    }
}



