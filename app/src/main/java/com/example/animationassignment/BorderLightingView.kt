package com.example.animationassignment

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import android.animation.ValueAnimator
import android.graphics.Matrix
import android.graphics.SweepGradient
import android.view.animation.LinearInterpolator

class BorderLightingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private var angle = 0f

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 20f // Increase the stroke width for wider light effect

        val animator = ValueAnimator.ofFloat(0f, 360f)
        animator.addUpdateListener { animation ->
            angle = animation.animatedValue as Float
            invalidate()
        }
        animator.duration = 4000
        animator.repeatMode = ValueAnimator.RESTART
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator()
        animator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = 100f

        val colors = intArrayOf(
            Color.TRANSPARENT,
            Color.parseColor("#0d90ae"),
            Color.TRANSPARENT,
            Color.parseColor("#900133"),
            Color.TRANSPARENT,
            Color.parseColor("#0d90ae"),
            Color.TRANSPARENT,
            Color.parseColor("#900133"),
            Color.TRANSPARENT,
            Color.parseColor("#0d90ae"),
            Color.TRANSPARENT,
            Color.parseColor("#900133"),
            Color.TRANSPARENT,
            Color.parseColor("#0d90ae")
        )

        val position = floatArrayOf(
            0f, 0.083f, 0.167f, 0.25f, 0.333f, 0.417f, 0.5f, 0.583f, 0.667f, 0.75f, 0.833f, 0.917f,0.96f, 1f
        )
        val shader = SweepGradient(centerX, centerY, colors, position)
        val matrix = Matrix()
        matrix.preRotate(angle, centerX, centerY)
        shader.setLocalMatrix(matrix)

        paint.shader = shader

        val rect = RectF(0f, 0f, width.toFloat(), height.toFloat())
        canvas.drawRoundRect(rect, radius, radius, paint)
    }
}
