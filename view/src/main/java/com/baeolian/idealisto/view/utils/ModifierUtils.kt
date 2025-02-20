package com.baeolian.idealisto.view.utils

import android.graphics.BlurMaskFilter
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.advancedShadow(
    color: Color? = null,
    borderRadius: Dp = 0.dp,
    blurRadius: Dp = 8.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Float = 0f,
    modifier: Modifier = Modifier,
) = composed {
    val defaultColor = MaterialTheme.colorScheme.scrim.copy(0.10f).toArgb()

    this.then(
        modifier.drawBehind {
            this.drawIntoCanvas {
                val paint = Paint()
                val frameworkPaint = paint.asFrameworkPaint()
                val spreadPixel = spread.dp.toPx()
                val leftPixel = (0f - spreadPixel) + offsetX.toPx()
                val topPixel = (0f - spreadPixel) + offsetY.toPx()
                val rightPixel = (this.size.width + spreadPixel)
                val bottomPixel = (this.size.height + spreadPixel)

                if (blurRadius != 0.dp) {
                    frameworkPaint.maskFilter = (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
                }

                frameworkPaint.color = color?.toArgb() ?: defaultColor

                it.drawRoundRect(
                    left = leftPixel,
                    top = topPixel,
                    right = rightPixel,
                    bottom = bottomPixel,
                    radiusX = borderRadius.toPx(),
                    radiusY = borderRadius.toPx(),
                    paint
                )
            }
        }
    )
}
