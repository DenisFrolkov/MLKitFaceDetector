package com.denis.mlkitfacedetector.feature_main.utils

import androidx.compose.ui.unit.Constraints
import com.denis.mlkitfacedetector.feature_main.data_class.PreviewScaleType
import com.denis.mlkitfacedetector.feature_main.data_class.SourceInfo

fun calculateScale(
    constraints: Constraints,
    sourceInfo: SourceInfo,
    scaleType: PreviewScaleType
): Float {
    val heightRatio = constraints.maxHeight.toFloat() / sourceInfo.height
    val widthRatio = constraints.maxWidth.toFloat() / sourceInfo.width
    return when (scaleType) {
        PreviewScaleType.FIT_CENTER -> kotlin.math.min(heightRatio, widthRatio)
        PreviewScaleType.CENTER_CROP -> kotlin.math.max(heightRatio, widthRatio)
    }
}