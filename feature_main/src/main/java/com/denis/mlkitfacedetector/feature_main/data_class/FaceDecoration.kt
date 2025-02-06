package com.denis.mlkitfacedetector.feature_main.data_class

import com.denis.mlkitfacedetector.feature_main.R

enum class FaceDecoration(val drawableRes: Int, val mood: Float?) {
    CONTOUR(R.drawable.contour, null),
    GLASSES(R.drawable.glasses, 1.6f),
    HAT(R.drawable.hat, 5f)
}