package com.clarkz.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MyView constructor(context: Context?, attrs: AttributeSet?): View(context, attrs){
    private var radius = 100f
    set(value) {
        field = value
        invalidate()
    }

    private val paint = Paint()

    init {
        paint.color = Color.GREEN
        paint.textSize = 8f
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(width.toFloat()/2, height.toFloat()/2, radius, paint)
    }

}